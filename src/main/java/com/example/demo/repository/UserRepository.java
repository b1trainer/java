package com.example.demo.repository;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUserName(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);

}
