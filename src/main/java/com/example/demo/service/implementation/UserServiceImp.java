package com.example.demo.service.implementation;

import com.example.demo.domain.exception.ResourceNotFoundException;
import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getByID", key = "#id")
    public User getByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getByUserName", key = "#username")
    public User getByUserName(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "UserService::getByID", key = "#user.id"),
            @CachePut(value = "UserService::getByUserName", key = "#user.username")
    })
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.update(user);
        return user;
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "UserService::getByID", key = "#user.id"),
            @Cacheable(value = "UserService::getByUserName", key = "#user.username")
    })
    public User create(User user) {
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exist");
        }

        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            throw new IllegalStateException("Password and pass confirmation do not match");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.create(user);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        userRepository.insertUserRole(user.getId(), Role.ROLE_USER);
        user.setRoles(roles);

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::isTaskOwner", key = "#userId + '.' + #taskId")
    public boolean isTaskOwner(Long userId, Long taskId) {
        return userRepository.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "UserService::getById", key = "#id")
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
