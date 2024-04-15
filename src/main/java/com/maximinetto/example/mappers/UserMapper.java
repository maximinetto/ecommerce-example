package com.maximinetto.example.mappers;

import org.springframework.stereotype.Component;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserDTOResponse;
import com.maximinetto.example.entities.User;

@Component
public class UserMapper {

  public User toEntity(UserDTO userDTO) {
    return User.builder()
        .id(userDTO.id())
        .firstName(userDTO.firstName())
        .lastName(userDTO.lastName())
        .email(userDTO.email())
        .password(userDTO.password())
        .build();
  }

  public UserDTOResponse toDTOResponse(User user) {
    return new UserDTOResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getAddress());
  }

  public UserDTO toDTO(User user){
    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
  }
}
