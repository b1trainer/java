package com.example.demo.repository;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUserName(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(@Param("userId") Long userId, @Param("role") Role role);

    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

    void delete(Long id);

}
