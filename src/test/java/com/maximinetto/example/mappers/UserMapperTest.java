package com.maximinetto.example.mappers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maximinetto.example.config.Beans;
import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.entities.Address;
import com.maximinetto.example.entities.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Beans.class)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testShouldMapAnUserToUserDTOCorrectly(){
    var password = passwordEncoder.encode("example");
    var user = User.builder()
                   .firstName("Maximiliano")
                   .lastName("Minetto")
                   .email("maximinetto@gmail.com")
                   .password(password)
                   .phoneNumber("+456654651651")
                   .build();

    var userDTO = userMapper.toDTO(user);
    
    Assertions.assertThat(userDTO).usingRecursiveComparison().isEqualTo(new UserDTO(null, "Maximiliano", "Minetto", "maximinetto@gmail.com", password, "+456654651651", null));

    user.setId(1l);

    userDTO = userMapper.toDTO(user);

    Assertions.assertThat(userDTO).usingRecursiveComparison().isEqualTo(new UserDTO(1l, "Maximiliano", "Minetto", "maximinetto@gmail.com", password, "+456654651651", null));

    user.setAddress(new Address(1L, "Artigas", "Uruguay", "55555", "Artigas", 2000, "1"));

    userDTO = userMapper.toDTO(user);

    Assertions.assertThat(userDTO).usingRecursiveComparison().isEqualTo(new UserDTO(1l, "Maximiliano", "Minetto", "maximinetto@gmail.com", password, "+456654651651", new Address(1L, "Artigas", "Uruguay", "55555", "Artigas", 2000, "1")));
  }
}
