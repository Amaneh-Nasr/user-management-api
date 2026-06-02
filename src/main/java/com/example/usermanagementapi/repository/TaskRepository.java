package com.example.usermanagementapi.repository;

import com.example.usermanagementapi.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();
    private long idCounter = 1;

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {

        if (task.getId() == null) {
            task.setId(idCounter++);
        }

        tasks.put(task.getId(), task);
        return task;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }
}