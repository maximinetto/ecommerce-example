package com.maximinetto.example.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserPaginate;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController()
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public Collection<User> getUserPaginate(UserPaginate paginate){
    return userService.paginateUsers(paginate);
  }

  @PostMapping()
  @ResponseStatus(code = HttpStatus.CREATED)
  public User createUser(@RequestBody UserDTO userDTO){
    return userService.createUser(userDTO);
  }

}
