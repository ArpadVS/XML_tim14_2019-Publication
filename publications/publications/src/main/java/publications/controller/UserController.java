package publications.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.MarshallingFailedException;
import publications.model.user.Role;
import publications.model.user.User;
import publications.service.UserService;
import publications.util.marshalling.MarshallUser;
import publications.util.marshalling.UnmarshallingUser;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@GetMapping(value = "/marshall", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> marshall() throws MarshallingFailedException{
		
		User user = new User();
		user.setFirst_name("Marieta");
		user.setLast_name("Rakos");
		user.setEmail("email");
		user.setPassword("pass");
		user.setUser_id("id");
		user.getRoles().add(Role.AUTHOR);
		user.getExpertise_list().add("expert");
		user.getExpertise_list().add("madjionicar");
		return new ResponseEntity<>(MarshallUser.marshall(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/unmarshall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> unmarshall(){
		return new ResponseEntity<>(UnmarshallingUser.unmarshall("./src/main/resources/data/xml/user1.xml"), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> create(@RequestBody User new_user) throws Exception{
		System.out.println(new_user.toString());
		return new ResponseEntity<>(userService.save(new_user), HttpStatus.CREATED);
	}
}
