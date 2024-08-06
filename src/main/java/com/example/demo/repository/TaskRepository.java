package com.example.demo.repository;

import com.example.demo.domain.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> findById(Long id);

    List<Task> findByUserId(Long userId);

    void assignToUserByID(Long taskId, Long userId);

    void update(Task task);

    void create(Task task);

    void delete(Long id);
}
