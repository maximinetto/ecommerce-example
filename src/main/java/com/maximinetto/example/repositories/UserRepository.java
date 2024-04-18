package com.maximinetto.example.repositories;

import java.util.Optional;

import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maximinetto.example.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  Window<User> findBy(KeysetScrollPosition position, Sort sort, Limit limit);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.address WHERE u.id = :id")
  Optional<User> findByIdWithAddress(@Param("id") Long id);
}
