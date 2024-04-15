package com.maximinetto.example.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.maximinetto.example.entities.Address;

@JsonInclude(value = Include.NON_NULL)
public record UserDTOResponse(Long id, String firstName, String lastName, String email, Address address) {
  
}
