package publications.model.user.converters;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import publications.model.user.User;
import publications.model.user.DTO.UserDTO;

@Component
public class UserConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO toDTO(User user, String token){
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        userDto.setToken(token);
        return  userDto;
    }

}
