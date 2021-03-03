package com.fico.todo.controller;

import com.fico.todo.model.Task;
import com.fico.todo.model.TaskApiResponse;
import com.fico.todo.repository.TaskRepository;
import com.fico.todo.repository.UserRepository;
import com.fico.todo.service.MyUserDetailsService;
import static com.fico.todo.utilities.Constants.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@RestController
@RequestMapping("api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping(path="tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task addTask(@RequestBody Task task){
        taskRepository.save(task);
        return task;
    }

    @PutMapping(path="tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task updateTask(@RequestBody Task task){
        taskRepository.save(task);
        return task;
    }


    @GetMapping(value = "tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks(Principal principal){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(roles.contains("ADMIN"))
            return taskRepository.findAll();
        System.out.println("Not authorized to do this operation");
        return Collections.emptyList();

    }

    @GetMapping(value = "tasks", consumes = MediaType.APPLICATION_JSON_VALUE, params = {"userId"})
    public ResponseEntity<TaskApiResponse> getTasksByUserId(@RequestParam(value="userId") Optional<Long> userId, Principal principal){
        Set<String> roles = userDetailsService.getRoleSet(principal);
        if(principal.getName()==userRepository.findById(userId).getUsername() || roles.contains("ADMIN")) {
            TaskApiResponse response = new TaskApiResponse("S01",
                    S01_TASK_API_RES,
                    "v1",
                    taskRepository.findByUserId(userId));
            return ResponseEntity.ok(response);
        }
        TaskApiResponse response = new TaskApiResponse("F01", F01_TASK_API_RES, VERSION_V1);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @GetMapping(value = "/tasks/{taskId}", consumes = "application/json")
    public Optional<Task> getTask(@PathVariable("taskId") Long taskId){
        return taskRepository.findById(taskId);
    }


    @DeleteMapping(value = "tasks/{taskId}", consumes = "application/json")
    public String deleteAlien(@PathVariable Long taskId){
        Task a = taskRepository.getOne(taskId);
        taskRepository.delete(a);
        return "Task deleted Successfully";
    }


}
