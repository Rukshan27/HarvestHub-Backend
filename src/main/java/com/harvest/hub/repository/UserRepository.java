package com.harvest.hub.repository;

import com.harvest.hub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByActiveTrueAndEmail(String email);

    List<User> findByActiveTrue();

    Optional<User> findByActiveTrueAndId(Long id);
}
