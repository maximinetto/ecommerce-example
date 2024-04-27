package com.maximinetto.example.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.maximinetto.example.entities.Address;

@JsonInclude(value = Include.NON_NULL)
public record UserDTO(Long id, String firstName, String lastName, String email,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password, String phoneNumber, Address address) {

}
