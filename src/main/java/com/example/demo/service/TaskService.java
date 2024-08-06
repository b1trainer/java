package com.example.demo.service;

import com.example.demo.domain.task.Task;

import java.util.List;

public interface TaskService {

    Task getByID(Long id);

    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task, Long id);

    void delete(Long id);

}
