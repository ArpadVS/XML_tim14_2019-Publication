package publications.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import publications.exceptions.NotFoundException;
import publications.model.user.Role;
import publications.model.user.User;
import publications.repository.UserRepository;
import publications.util.dom_parser.DOMParser;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
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
		found.getRoles().add(role);
		return updateSave(found);
	}
	
	public User removeRole(String userId, Role role) throws Exception {
		User found = findById(userId);
		found.getRoles().remove(role);
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
}
