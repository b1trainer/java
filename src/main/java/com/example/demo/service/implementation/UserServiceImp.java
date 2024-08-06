package com.example.demo.service.implementation;

import com.example.demo.domain.user.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Override
    public User getByID(Long id) {
        return null;
    }

    @Override
    public User getByUserName(String username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
