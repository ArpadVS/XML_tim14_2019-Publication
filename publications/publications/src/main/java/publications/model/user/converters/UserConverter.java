package publications.model.user.converters;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import publications.model.DTO.RegisterUserDTO;
import publications.model.DTO.UserDTO;
import publications.model.user.Role;
import publications.model.user.User;

@Component
public class UserConverter {

    private ModelMapper modelMapper = new ModelMapper();
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public UserDTO toDTO(User user, String token){
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        userDto.setToken(token);
        return  userDto;
    }
    
    public User toUser(RegisterUserDTO dto) {
    	User user = modelMapper.map(dto, User.class);
    	user.getRole().add(Role.values()[dto.getRole()]);
    	user.setPassword(passwordEncoder.encode(dto.getPassword()));
    	return user;
    }

}
