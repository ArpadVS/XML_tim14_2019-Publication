package publications.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publications.exceptions.NotFoundException;
import publications.model.user.User;
import publications.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) throws Exception {
		return userRepository.save(user);
	}
	
	public User findById(String id) throws NotFoundException {
		String xPathExpression = String.format("//user[@user_id='%s']", id);
		return userRepository.findByExpression(xPathExpression);
	}
	
	public User findByEmail(String email) throws NotFoundException {
		String xPathExpression = String.format("//user[email='%s']", email);
		return userRepository.findByExpression(xPathExpression);
	}
	
	
	
}
