package com.example.usermanagementapi.service;

import com.example.usermanagementapi.model.Task;
import com.example.usermanagementapi.repository.TaskRepository;
import com.example.usermanagementapi.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        return task;
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public void delete(Long id) {
        Task task = repository.findById(id);

        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        repository.delete(id);
    }

    public Task update(Long id, Task task) {

        Task existing = repository.findById(id);

        if (existing == null) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }

        existing.setTitle(task.getTitle());
        existing.setCompleted(task.isCompleted());

        return repository.save(existing);
    }
}