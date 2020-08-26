package publications.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import publications.auth.JwtAuthenticationRequest;
import publications.exceptions.MarshallingFailedException;
import publications.exceptions.NotFoundException;
import publications.model.user.Role;
import publications.model.user.User;
import publications.model.user.converters.UserConverter;
import publications.service.UserService;
import publications.util.TokenUtils;
import publications.util.marshalling.MarshallUser;
import publications.util.marshalling.UnmarshallingUser;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
    public AuthenticationManager authenticationManager;

	@Autowired
	public TokenUtils tokenUtils;
	
	@Autowired
	public UserConverter userConverter;
	
	@GetMapping(value = "/marshall", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> marshall() throws MarshallingFailedException{
		
		User user = new User();
		user.setUsername("marieta");
		user.setFirst_name("Marieta");
		user.setLast_name("Rakos");
		user.setEmail("email");
		user.setPassword("pass");
		user.setUser_id("id");
		user.getRoles().add(Role.ROLE_AUTHOR);
		user.getExpertise().add("expert");
		user.getExpertise().add("madjionicar");
		return new ResponseEntity<>(MarshallUser.marshall(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/unmarshall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> unmarshall(){
		return new ResponseEntity<>(UnmarshallingUser.unmarshallFromFile("./src/main/resources/data/xml/user1.xml"), HttpStatus.OK);
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
	public ResponseEntity<?> getUserByEmail(@PathVariable(value = "email") String userEmail) throws NotFoundException{
		// TODO konvertovati u neki DTO, ne sme se vratiti password
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
		return new ResponseEntity<>(userService.getUserPDF(userId).toByteArray(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/author")
	@PreAuthorize("hasRole('AUTHOR')")
	public String test0( ) { 
		return "AUTHOR WORKS>> ";
	}

	@GetMapping(value = "/editor")
	@PreAuthorize("hasRole('EDITOR')")
	public String test1() { 
		return "EDITOR WORKSO";
	}

	@GetMapping(value = "/reviewer")
	@PreAuthorize("hasRole('REVIEWER')")
	public String test2() { 
		return "REVIEWER WORKS";
	}
	
	@GetMapping(value = "/svi") 
	public String test3() { 
		return "ALL WORKS";
	}
	
  @SuppressWarnings("rawtypes")
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public ResponseEntity logOut(HttpServletRequest request) {
	  //ne radi
      SecurityContextHolder.getContext().setAuthentication(null);
      return new ResponseEntity(HttpStatus.OK);
  }


  @RequestMapping(value = "/login", method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> logIn(@RequestBody JwtAuthenticationRequest authenticationRequest) {
      final Authentication authentication = authenticationManager
                              .authenticate(new UsernamePasswordAuthenticationToken(
                                      authenticationRequest.getUsername(),
                                      authenticationRequest.getPassword()
                              ));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      User user = (User)authentication.getPrincipal();
      List<Role> roles = user.getRoles();
      String role = roles.get(0).getAuthority();
      System.out.println(role);
      return ResponseEntity.ok( userConverter.toDTO(user, tokenUtils.generateToken(user.getUsername(), role)));
  }
}
