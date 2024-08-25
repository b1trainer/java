package com.example.demo.service.implementation;

import com.example.demo.domain.exception.ResourceNotFoundException;
import com.example.demo.domain.task.Status;
import com.example.demo.domain.task.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getByID(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {

        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {

        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.update(task);

        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {

        task.setStatus(Status.TODO);
        taskRepository.create(task);
        taskRepository.assignToUserByID(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);

    }
}
