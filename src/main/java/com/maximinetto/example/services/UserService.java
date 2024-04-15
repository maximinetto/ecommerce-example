package com.maximinetto.example.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserDTOResponse;
import com.maximinetto.example.dtos.UserPaginate;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.mappers.UserMapper;
import com.maximinetto.example.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  public Collection<User> paginateUsers(UserPaginate paginatedFields) {
    Map<String, Object> map = new HashMap<>();
    paginatedFields.getId().ifPresent((_id) -> map.put("id", _id));
    paginatedFields.getFirstName().ifPresent((_firsName) -> map.put("firstName", _firsName));
    paginatedFields.getLastName().ifPresent((_lastName) -> map.put("lastName", _lastName));

    KeysetScrollPosition position = map.isEmpty() ? ScrollPosition.keyset() : ScrollPosition.forward(map);
    String[] fields = map.isEmpty() ? new String[]{"firstName", "lastName", "id"}: map.keySet().toArray(new String[map.size()]);
    Sort sort = Sort.by(Sort.Direction.ASC, fields);
    Limit _limit = Limit.of(paginatedFields.getLimit().orElse(10));
    Window<User> users = userRepository.findBy(position, sort, _limit);

    return users.stream().collect(Collectors.toList());
  }

  public UserDTOResponse saveUser(final UserDTO userDTO) {
    var user = userMapper.toEntity(userDTO);

    if(user.getId() != null) {
      user = userRepository.findById(user.getId()).map((User userDB) -> {
        userDB.setFirstName(userDTO.firstName());
        userDB.setLastName(userDTO.lastName());
        userDB.setEmail(userDTO.email());
        userDB.setPassword(userDTO.password());
        return userDB;
      }).orElse(user); 
    }

    String hashedPassword = passwordEncoder.encode(user.getPassword());

    user.setPassword(hashedPassword);

    log.info("User: {}", user);

    var userSaved = userRepository.save(user);
    return userMapper.toDTOResponse(userSaved);
  }

}
