package publications.service;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publications.model.user.User;
import publications.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) throws Exception {
		return userRepository.save(user);
	}
}
