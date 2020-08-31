package publications.service;


import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import publications.exceptions.MarshallingFailedException;
import publications.exceptions.NotFoundException;
import publications.model.user.Role;
import publications.model.user.User;
import publications.repository.UserRepository;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.MarshallUser;
import publications.util.transformations.HTMLTransformer;
import publications.util.transformations.XSLFOTransformer;

import static publications.util.constants.ApplicationConstants.*;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HTMLTransformer htmlTransformer;
	
	@Autowired 
	XSLFOTransformer xslfoTransformer;
	
	public User save(User user) throws Exception {
		return userRepository.save(user);
	}
	
	/*
	 * Does not generate new id
	 */
	public User updateSave(User user) throws Exception {
		return userRepository.updateSave(user);
	}
	
	public User findById(String id) throws NotFoundException {
		String xPathExpression = String.format("//user[@user_id='%s']", id);
		return userRepository.findByExpression(xPathExpression);
	}
	
	public User findByEmail(String email) throws NotFoundException {
		String xPathExpression = String.format("//user[email='%s']", email);
		return userRepository.findByExpression(xPathExpression);
	}
	
	public String getUserHTML(String id) throws Exception {
		String xPathExpression = String.format("//user[@user_id='%s']", id);
		User user = userRepository.findByExpression(xPathExpression);
		String xmlUser = MarshallUser.marshall(user);
		String res = htmlTransformer.generateHTML(xmlUser, XSLT_PATH_PREFIX+"/user.xsl");
		System.out.println(res);
		return res;
	}
	
	public ByteArrayOutputStream getUserPDF(String id) throws Exception {
		String xPathExpression = String.format("//user[@user_id='%s']", id);
		User user = userRepository.findByExpression(xPathExpression);
		String xmlUser = MarshallUser.marshall(user);
		return xslfoTransformer.generatePDF(xmlUser, XSLT_FO_PATH_PREFIX + "/user_fo.xsl");
	}
	public String delete(String userId) throws Exception {
		userRepository.removeUser(userId);
		return "Successfully deleted user";
	}
	
	public User update(String userId, User user) throws Exception {
		User found = findById(userId);
		found.setEmail(user.getEmail());
		found.setBiography(user.getBiography());
		found.setFirst_name(user.getFirst_name());
		found.setLast_name(user.getLast_name());
		delete(userId);
		return updateSave(found);
	}
	
	public User addRole(String userId, Role role) throws Exception {
		User found = findById(userId);
		found.getRole().add(role);
		return updateSave(found);
	}
	
	public User removeRole(String userId, Role role) throws Exception {
		User found = findById(userId);
		found.getRole().remove(role);
		return updateSave(found);
	}
	
	public User addExpertise(String userId, String expertise) throws Exception {
		User found = findById(userId);
		found.getExpertise().add(expertise);
		return updateSave(found);
	}
	
	public User removeExpertise(String userId, String expertise) throws Exception {
		User found = findById(userId);
		found.getExpertise().remove(expertise);
		return updateSave(found);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		String xPathExpression = String.format("//user[username='%s']", username);
		UserDetails userDetails = null;
		try {
			userDetails = userRepository.findByExpression(xPathExpression);
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException("User with user name: " + username + " not found");
		}
		if (userDetails == null) {
			throw new UsernameNotFoundException("User with user name: " + username + " not found");
		}
		return userDetails;
	}
}
