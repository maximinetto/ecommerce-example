package com.maximinetto.example.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maximinetto.example.config.Beans;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.exceptions.UserAlreadyExistsException;
import com.maximinetto.example.exceptions.UserNotFoundException;
import com.maximinetto.example.mappers.UserMapper;
import com.maximinetto.example.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Beans.class)
public class UserServiceTest {

  private UserService userService;

  private UserRepository userMockRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void beforeAll(){
    userMockRepository = Mockito.mock(UserRepository.class);
    userService = new UserService(userMockRepository, passwordEncoder, userMapper);
  }

  @Test
  public void shouldCreateAnUserSuccessfullyWithoutID() throws UserAlreadyExistsException{
    var user = User.builder()
                   .firstName("Maximiliano")
                   .lastName("Minetto")
                   .email("maximinetto@gmail.com")
                   .password(passwordEncoder.encode("12345678"))
                   .build();

    var expectedUser = User
    .builder()
    .id(1L)
    .firstName(user.getFirstName())
    .lastName(user.getLastName())
    .email(user.getEmail())
    .password(user.getPassword()).build();
                  
    Mockito.when(userMockRepository.save(any(User.class))).thenReturn(expectedUser);
      
    var userDTOExpected = userMapper.toDTO(expectedUser);
    var userResponse = userService.saveUser(userMapper.toDTO(user)); 
    
    Assertions.assertNotNull(userResponse);
    Assertions.assertEquals(userResponse, userDTOExpected);
  }

  @Test
  public void shouldCreateAnUserSuccessfullyWithID() throws UserAlreadyExistsException{
    var user = User.builder()
                   .id(2L)
                   .firstName("Maximiliano")
                   .lastName("Minetto")
                   .email("maximinetto@gmail.com")
                   .password(passwordEncoder.encode("12345678"))
                   .build();

    var expectedUser = User
    .builder()
    .id(1L)
    .firstName(user.getFirstName())
    .lastName(user.getLastName())
    .email(user.getEmail())
    .password(user.getPassword()).build();
                  
    Mockito.when(userMockRepository.save(any(User.class))).thenReturn(expectedUser);
      
    var userDTOExpected = userMapper.toDTO(expectedUser);
    var userResponse = userService.saveUser(userMapper.toDTO(user)); 
    
    Assertions.assertNotNull(userResponse);
    Assertions.assertEquals(userResponse, userDTOExpected);
  }

  @Test
  public void shouldDeleteAnExistingUser() throws UserNotFoundException {
    var user = User.builder()
    .id(1L)
    .firstName("Maximiliano")
    .lastName("Minetto")
    .email("maximinetto@gmail.com")
    .password(passwordEncoder.encode("12345678"))
    .build();

    Mockito.when(userMockRepository.findById(user.getId())).thenReturn(Optional.of(user));
    Mockito.doNothing().when(userMockRepository).delete(user);

    userService.deleteUser(user.getId());

    verify(userMockRepository, times(1)).delete(user);
  }

}
