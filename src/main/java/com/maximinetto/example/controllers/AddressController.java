package com.maximinetto.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maximinetto.example.dtos.UserDTOResponse;
import com.maximinetto.example.entities.Address;
import com.maximinetto.example.exceptions.UserNotFoundException;
import com.maximinetto.example.services.AddressService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/users/{id}/addresses")
@RequiredArgsConstructor
public class AddressController {
  private final AddressService addressService;

  @PutMapping()
  @ResponseStatus(code = HttpStatus.OK)
  public UserDTOResponse saveAddress(@PathVariable Long id, @RequestBody Address address) throws UserNotFoundException {
    return addressService.saveAddress(id, address);
  } 
}
