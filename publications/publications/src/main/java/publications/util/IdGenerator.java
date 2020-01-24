package publications.util;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import publications.exceptions.NotFoundException;
import publications.model.user.User;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.UnmarshallingUser;
import static publications.util.constants.ApplicationConstants.*;

@Component
public class IdGenerator {

	@Autowired
	ExistDBManagement dbManagement;
	
	public int generateId() throws NotFoundException {
		try {
			//iz nekog razloga nmg da selektujem samo jedan element unutar usera
			String exp = "for $u in /. return $u";
			System.out.println(exp);
			ResourceSet result = dbManagement.executeXQuery(USER_COLLECTION_ID, exp, "");

			if (result == null) {
				throw new Exception();
			}
			
			ResourceIterator i = result.getIterator();
			Resource res = null;
			User user = null;

			int max_id = 0;
			while (i.hasMoreResources()) {
				System.out.println("******");
				try {
					res = i.nextResource();
					System.out.println(res.getContent().toString());
					user = UnmarshallingUser.unmarshall((res.getContent().toString()));
					int current_id = Integer.parseInt(user.getUser_id().split("-")[1]);
					if (current_id > max_id) {
						max_id = current_id;
					}
				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println(max_id);
			return ++max_id;

		} catch (Exception e) {
			System.out.println("greska");
			e.printStackTrace();
			return -1;
			
		}
	}
}
