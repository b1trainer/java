package com.example.demo.web.controller;

import com.example.demo.domain.task.Task;
import com.example.demo.service.TaskService;
import com.example.demo.web.dto.task.TaskDto;
import com.example.demo.web.dto.validation.OnUpdate;
import com.example.demo.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);

        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getByID(id);

        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

}
