package com.maximinetto.example.mappers;

import org.springframework.stereotype.Component;

import com.maximinetto.example.dtos.UserDTO;
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
        .phoneNumber(userDTO.phoneNumber())
        .build();
  }

  public User bindEntity(UserDTO userDTO, User user) {
    user.setId(userDTO.id());
    user.setFirstName(userDTO.firstName());
    user.setLastName(userDTO.lastName());
    user.setEmail(userDTO.email());
    user.setPassword(userDTO.password());
    user.setPhoneNumber(userDTO.phoneNumber());
    return user;
  }

  public UserDTO toDTO(User user) {
    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
        user.getPhoneNumber(), user.getAddress());
  }
}
