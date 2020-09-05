package publications.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import publications.exceptions.MarshallingFailedException;
import publications.exceptions.NotFoundException;
import publications.model.DTO.ScientificPaperDTO;
import publications.model.paper.ScientificPaper;
import publications.model.paper.TAuthor;
import publications.model.user.User;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.MarshallUser;
import publications.util.marshalling.UnmarshallingUtil;
import static publications.util.constants.ApplicationConstants.*;

@Repository
public class UserRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;
	
	@Autowired
	UnmarshallingUtil unmarshallingUtil;

	public User save(User user) throws Exception {
		String id = idGenerator.generateRandomID(USER_COLLECTION_ID, USER_ID_PREFIX);
		user.setUser_id(id);
		String user_xml = MarshallUser.marshall(user);
		System.out.println(user_xml);
		Document document = domParser.buildDocument(user_xml, USER_XSD);
		dbManagement.save(USER_COLLECTION_ID, user.getUser_id(), user_xml);
		return user;
	}

	public User updateSave(User user) throws Exception {
		String user_xml = MarshallUser.marshall(user);
		dbManagement.save(USER_COLLECTION_ID, user.getUser_id(), user_xml);
		return user;
	}

	public User findByExpression(String expression) throws NotFoundException {
		try {
			// String xPathExpression = String.format("//user[@user_id='%s']", id);
			ResourceSet result = dbManagement.executeXPath(USER_COLLECTION_ID, expression, TARGET_NAMESPACE_TIM14);

			if (result == null) {
				return null;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			User user = null;

			if (!i.hasMoreResources()) {
				System.out.println("Not found");
				throw new NotFoundException("Could not find requested user");
			}
			while (i.hasMoreResources()) {

				try {
					res = i.nextResource();
					user = unmarshallingUtil.unmarshall((res.getContent().toString()));

				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

			return user;

		} catch (Exception e) {
			throw new NotFoundException("Could not find requested user");
		}
	}

	public ArrayList<User> findMultipleByExpression(String expression){
		ArrayList<User> found = new ArrayList<User>();
		try {
			ResourceSet result = dbManagement.executeXPath(USER_COLLECTION_ID, expression,
					TARGET_NAMESPACE_TIM14);

			if (result == null) {
				return found;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			User user = null;

			if (!i.hasMoreResources()) {
				System.out.println("Not found");
				throw new NotFoundException("Could not find requested scientific paper");
			}
			while (i.hasMoreResources()) {
				ScientificPaperDTO dto = new ScientificPaperDTO();
				try {
					res = i.nextResource();
					user = unmarshallingUtil.unmarshall((res.getContent().toString()));
					
					found.add(user);

				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			//throw new NotFoundException("Could not find requested scientific paper");
			return found;
		}

		return found;
	
	}
	
	public void removeUser(String userId) throws Exception {
		long flag = dbManagement.delete(USER_COLLECTION_ID, userId);
		if (flag == 0) {
			throw new NotFoundException("Could not find user");
		}

	}

}
