package publications.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import publications.model.user.User;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.MarshallUser;

@Repository
public class UserRepository {

	@Autowired
	ExistDBManagement dbManagement;
	
	public static String collectionId = "/db/sample/user";
	
	public User save(User user) throws Exception {
		//String id = "user";
		//user.setUser_id(id);
		//generisati id
		String user_xml = MarshallUser.marshall(user);
		dbManagement.save(collectionId, user.getUser_id(), user_xml);
		return user;
	}
	
	/*public User findOneById(String id) {
		
	}*/
}
