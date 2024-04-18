package com.maximinetto.example.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maximinetto.example.dtos.UserDTOResponse;
import com.maximinetto.example.entities.Address;
import com.maximinetto.example.exceptions.UserNotFoundException;
import com.maximinetto.example.mappers.UserMapper;
import com.maximinetto.example.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  public UserDTOResponse saveAddress(Long userId, Address address) throws UserNotFoundException {
    var user = userRepository.findByIdWithAddress(userId)
        .orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario con id: " + userId));

    Address addressToSave = Optional.ofNullable(user.getAddress()).map(addressDB -> {
      addressDB.setApto(address.getApto());
      addressDB.setCity(address.getCity());
      addressDB.setCountry(address.getCountry());
      addressDB.setPostcode(address.getPostcode());
      addressDB.setStreetName(address.getStreetName());
      addressDB.setStreetNumber(address.getStreetNumber());
      return addressDB;
    }).orElse(address);

    user.setAddress(addressToSave);
    var userSaved = userRepository.save(user);
    return userMapper.toDTOResponse(userSaved);
  }
}
