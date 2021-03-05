package com.fico.todo.service;

import com.fico.todo.model.Task;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task create(Task task);

    List<Task> findAll();

    List findByUserId(Optional<Long> userId, Principal principal);

    Optional<Task> findById(Long taskId);

    void delete(Long taskId);



}
