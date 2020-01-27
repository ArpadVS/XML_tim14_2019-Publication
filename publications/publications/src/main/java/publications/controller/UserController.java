package publications.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.MarshallingFailedException;
import publications.exceptions.NotFoundException;
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
		user.getExpertise().add("expert");
		user.getExpertise().add("madjionicar");
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
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") String userId) throws NotFoundException{
		return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String userEmail) throws NotFoundException{
		return new ResponseEntity<>(userService.findByEmail(userEmail), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> delete(@PathVariable(value = "id") String userId) throws Exception{
		return new ResponseEntity<>(userService.delete(userId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<User> update(@PathVariable(value = "id")String userId, @RequestBody User user) throws Exception{
		
		return new ResponseEntity<>(userService.update(userId, user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getUserHTML(@PathVariable(value = "id") String userId) throws Exception{
		return new ResponseEntity<>(userService.getUserHTML(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getUserPDF(@PathVariable(value = "id") String userId) throws Exception{
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
