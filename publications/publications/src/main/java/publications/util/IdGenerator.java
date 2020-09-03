package publications.util;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import publications.exceptions.NotFoundException;
import publications.model.user.User;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.UnmarshallingUtil;
import static publications.util.constants.ApplicationConstants.*;
import java.security.SecureRandom;

@Component
public class IdGenerator {

	@Autowired
	ExistDBManagement dbManagement;

	@Bean
	public String alphabet() {
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	}

	@Bean
	public SecureRandom random() {
		return new SecureRandom();
	}

	private String randomID(int length, int spacing, char spacerChar) {
		String str = "";
		int spacer = 0;
		while (length > 0) {
			if (spacer == spacing) {
				str += spacerChar;
				spacer = 0;
			}
			length--;
			spacer++;
			str += randomChar();
		}
		return str;
	}

	public String generateRandomID(String collection_id, String prefix) throws Exception {
		// generisanje random id-a
		String id = randomID(6, 3, '-');

		// provera da li postoji dokument sa tim id-em
		// skoro nikad nece postojati ali za svaki slucaj
		XMLResource res = null;
		try {
			res = dbManagement.findOne(collection_id, prefix + id);
		} catch (NullPointerException e) {
			System.out.println("Collection not existing");
		}
		boolean found = false;
		if (res != null)
			found = true;
		while (found) {
			id = randomID(6, 3, '-');
			res = dbManagement.findOne(collection_id, prefix + id);
			if (res != null) {
				found = true;
			} else {
				found = false;
			}
		}

		return prefix + id;
	}

	private char randomChar() {
		return alphabet().charAt(random().nextInt(alphabet().length()));
	}

//	public int generateId(String collection_id, String entity) throws NotFoundException {
//		try {
//			// String exp = "for $u in /. return $u/user";
//			// System.out.println(exp);
//			// ResourceSet result = dbManagement.executeXQuery(USER_COLLECTION_ID, exp, "");
//			ResourceSet result = dbManagement.executeXPath(collection_id, "data(//" + entity + "/@" + entity + "_id)");
//			if (result == null) {
//				throw new Exception();
//			}
//
//			ResourceIterator i = result.getIterator();
//			Resource res = null;
//
//			int max_id = 0;
//			while (i.hasMoreResources()) {
//				System.out.println("******");
//				try {
//					res = i.nextResource();
//					System.out.println(res.getContent().toString());
//					int current_id = Integer.parseInt(res.getContent().toString().split("-")[1]);
//					if (current_id > max_id) {
//						max_id = current_id;
//					}
//				} finally {
//					// don't forget to cleanup resources
//					try {
//						((EXistResource) res).freeResources();
//					} catch (XMLDBException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//			System.out.println(max_id);
//			return ++max_id;
//
//		} catch (Exception e) {
//			System.out.println("error");
//			e.printStackTrace();
//			return -1;
//
//		}
//	}
}
