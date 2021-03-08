package com.fico.todo.service;

import com.fico.todo.exception.OperationNotAllowedException;
import com.fico.todo.exception.TaskNotFoundException;
import com.fico.todo.model.Task;
import com.fico.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

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

    public List<Task> findAll(Principal principal){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(roles.contains("ADMIN"))
            return taskRepository.findAll();
        else
            throw new OperationNotAllowedException();
    }

    public List findByUserId(Optional<Long> userId, Principal principal){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(principal.getName()==userService.findById(userId).getUsername() || roles.contains("ADMIN")) {
            return taskRepository.findByUserId(userId);
        }
        throw new OperationNotAllowedException();
    }

    public Optional<Task> findById(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(!task.isPresent()){
            //return null;
            throw new TaskNotFoundException();
        }
        return task;
    }

    public void delete(Long taskId){
        if(taskRepository.existsById(taskId)){
            Task task = taskRepository.getOne(taskId);
            taskRepository.delete(task);
        }
        else {
            throw new TaskNotFoundException();
        }
    }




}
