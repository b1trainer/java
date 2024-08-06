package com.example.demo.service;

import com.example.demo.domain.user.User;

public interface UserService {

    User getByID(Long id);

    User getByUserName(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);
}
