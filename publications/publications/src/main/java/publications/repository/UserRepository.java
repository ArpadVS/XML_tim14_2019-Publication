package publications.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import publications.exceptions.NotFoundException;
import publications.model.user.User;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.MarshallUser;
import publications.util.marshalling.UnmarshallingUser;
import static publications.util.constants.ApplicationConstants.*;

@Repository
public class UserRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;
	
	public User save(User user) throws Exception {
		String id = USER_ID_PREFIX + idGenerator.generateId();
		user.setUser_id(id);
		String user_xml = MarshallUser.marshall(user);
		dbManagement.save(USER_COLLECTION_ID, user.getUser_id(), user_xml);
		return user;
	}

	public User findByExpression(String expression) throws NotFoundException {
		try {
			//String xPathExpression = String.format("//user[@user_id='%s']", id);
			ResourceSet result = dbManagement.executeXPath(USER_COLLECTION_ID, expression);

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
					user = UnmarshallingUser.unmarshall((res.getContent().toString()));

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
	
	
}
