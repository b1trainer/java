package com.example.demo.repository.implementation;

import com.example.demo.domain.task.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImp implements TaskRepository {

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return null;
    }

    @Override
    public void assignToUserByID(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void delete(Long id) {

    }
}
