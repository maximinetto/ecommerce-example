package com.maximinetto.example.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.maximinetto.example.dtos.UserDTO;
import com.maximinetto.example.dtos.UserPaginate;
import com.maximinetto.example.entities.User;
import com.maximinetto.example.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

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

  public User createUser(UserDTO userDTO) {
    final var user = User.builder()
        .firstName(userDTO.firstName())
        .lastName(userDTO.lastName())
        .email(userDTO.email())
        .password(userDTO.password())
        .build();

    return userRepository.save(user);
  }

}
