package com.maximinetto.example.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserPaginate;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.exceptions.UserAlreadyExistsException;
import com.maximinetto.example.exceptions.UserNotFoundException;
import com.maximinetto.example.mappers.UserMapper;
import com.maximinetto.example.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  public Collection<UserDTO> paginateUsers(UserPaginate paginatedFields) {
    Map<String, Object> map = new HashMap<>();
    paginatedFields.getId().ifPresent((_id) -> map.put("id", _id));
    paginatedFields.getFirstName().ifPresent((_firsName) -> map.put("firstName", _firsName));
    paginatedFields.getLastName().ifPresent((_lastName) -> map.put("lastName", _lastName));

    KeysetScrollPosition position = map.isEmpty() ? ScrollPosition.keyset() : ScrollPosition.forward(map);
    String[] fields = map.isEmpty() ? new String[] { "firstName", "lastName", "id" }
        : map.keySet().toArray(new String[map.size()]);
    Sort sort = Sort.by(Sort.Direction.ASC, fields);
    Limit _limit = Limit.of(paginatedFields.getLimit().orElse(10));
    Window<User> users = userRepository.findBy(position, sort, _limit);

    return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
  }

  public UserDTO getUser(Long id) throws UserNotFoundException {
    return userRepository.findById(id).map(userMapper::toDTO)
        .orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario con id: " + id));
  }

  public UserDTO saveUser(final UserDTO userDTO) throws UserAlreadyExistsException{
    var user = userMapper.toEntity(userDTO);

    if (user.getId() != null) {
      user = userRepository.findById(user.getId()).map((User userDB) -> userMapper.bindEntity(userDTO, userDB))
          .orElse(user);
    }

    String hashedPassword = passwordEncoder.encode(user.getPassword());

    user.setPassword(hashedPassword);

    try {
      var userSaved = userRepository.save(user);
      return userMapper.toDTO(userSaved);
    } catch (DataIntegrityViolationException exception) {
      throw new UserAlreadyExistsException("El usuario con email " + userDTO.email() + " ya existe en el sistema");
    }
  }

  public void deleteUser(final Long id) throws UserNotFoundException {
    var user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario con id: " + id));
    userRepository.delete(user);
  }

}
