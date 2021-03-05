package com.fico.todo.service;

import com.fico.todo.model.Task;
import com.fico.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthMyUserDetailsService userDetailsService;

    @Autowired
    private AuthUserService userService;

    public Task create(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List findByUserId(Optional<Long> userId, Principal principal){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(principal.getName()==userService.findById(userId).getUsername() || roles.contains("ADMIN")) {
            return taskRepository.findByUserId(userId);
        }
        return Collections.emptyList();
    }

    public Optional<Task> findById(Long taskId){
        return taskRepository.findById(taskId);
    }

    public void delete(Long taskId){
        Task task = taskRepository.getOne(taskId);
        taskRepository.delete(task);

    }




}
