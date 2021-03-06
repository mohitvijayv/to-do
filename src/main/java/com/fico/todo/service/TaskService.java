package com.fico.todo.service;

import com.fico.todo.model.Task;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task create(Task task);

    List<Task> findAll(Principal principal);

    List findByUserId(Optional<Long> userId, Principal principal);

    Task findById(Long taskId);

    void delete(Long taskId);



}
