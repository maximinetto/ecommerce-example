package com.maximinetto.example.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserPaginate;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.exceptions.UserAlreadyExistsException;
import com.maximinetto.example.exceptions.UserNotFoundException;
import com.maximinetto.example.services.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public Collection<UserDTO> getUserPaginate(UserPaginate paginate){
    return userService.paginateUsers(paginate);
  }

  @GetMapping("/{id}")
  public UserDTO getUser(@PathVariable Long id) throws UserNotFoundException {
      return userService.getUser(id);
  }
  

  @PutMapping()
  @ResponseStatus(code = HttpStatus.OK)
  public UserDTO saveUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException{
    return userService.saveUser(userDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) throws UserNotFoundException{
    userService.deleteUser(id);
  }

}
