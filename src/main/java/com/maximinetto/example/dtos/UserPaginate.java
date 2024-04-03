package com.maximinetto.example.dtos;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPaginate {
  private Optional<Long> id = Optional.empty();

  private Optional<String> firstName = Optional.empty();

  private Optional<String> lastName = Optional.empty();

  private Optional<Integer> limit = Optional.empty();
}
