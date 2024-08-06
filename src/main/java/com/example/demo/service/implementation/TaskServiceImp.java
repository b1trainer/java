package com.example.demo.service.implementation;

import com.example.demo.domain.task.Task;
import com.example.demo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {
    @Override
    public Task getByID(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
