package com.maximinetto.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.maximinetto.example.mappers.UserMapper;
import com.maximinetto.example.services.UserService;

@TestConfiguration
public class Beans {
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserMapper userMapper(){
    return new UserMapper();
  }

}