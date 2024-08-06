package com.example.demo.repository.implementation;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
