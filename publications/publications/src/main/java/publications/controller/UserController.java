package publications.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.MarshallingFailedException;
import publications.marshalling.MarshallUser;
import publications.marshalling.UnmarshallingUser;
import publications.model.user.Role;
import publications.model.user.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@GetMapping(value = "/marshall", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> marshall() throws MarshallingFailedException{
		
		User user = new User();
		user.setFirst_name("Marieta");
		user.setLast_name("Rakos");
		user.setEmail("email");
		user.setPassword("pass");
		user.setUser_id("id");
		user.setRoles(new ArrayList<Role>());
		user.getRoles().add(Role.AUTHOR);
		return new ResponseEntity<>(MarshallUser.marshall(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/unmarshall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> unmarshall(){
		
		
		return new ResponseEntity<>(UnmarshallingUser.unmarshall("./src/main/resources/data/xml/user1.xml"), HttpStatus.OK);
	}
}
