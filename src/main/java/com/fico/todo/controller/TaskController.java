package com.fico.todo.controller;

import com.fico.todo.exception.TaskNotFoundException;
import com.fico.todo.model.Task;
import com.fico.todo.model.TaskApiResponse;
import com.fico.todo.service.AuthMyUserDetailsService;
import static com.fico.todo.utilities.Constants.*;

import com.fico.todo.service.TaskServiceImpl;
import com.fico.todo.service.AuthUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;


@RestController
@RequestMapping("api/v1/")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private AuthMyUserDetailsService userDetailsService;

    @Autowired
    private AuthUserService userService;


    @PostMapping(path="tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new task", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new Task"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity addTask(@ApiParam(value = "Information of new Task") @RequestBody Task task){
        taskService.create(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping(path="tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an existing task", response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new Task"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity updateTask(@ApiParam(value = "Information of task to be updated") @RequestBody Task task){
        taskService.create(task);
        return new ResponseEntity(task, HttpStatus.CREATED);
    }


    @GetMapping(value = "tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get a list of tasks", notes = "Get the list of all the tasks(admin only), pass user-id as parameter to get all the tasks associated with that user",response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 404, message = "No tasks found"),
            @ApiResponse(code = 403, message = "Not Authorized to perform this operation")
    })
     public ResponseEntity getTasks(Principal principal){
            return new ResponseEntity(taskService.findAll(principal), HttpStatus.OK);

    }

    @GetMapping(value = "tasks", produces = MediaType.APPLICATION_JSON_VALUE, params = {"userId"})
    @ApiOperation(value = "get a list of tasks", notes = "Get the list of all the tasks(admin only), pass user-id as parameter to get all the tasks associated with that user",response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 404, message = "No tasks found"),
            @ApiResponse(code = 403, message = "Not Authorized to perform this operation")
    })
    public ResponseEntity getTasksByUserId(@ApiParam(value = "user Id", required = true) @RequestParam(value="userId") Optional<Long> userId, Principal principal){

          List tasksList = taskService.findByUserId(userId, principal);
              return ResponseEntity.ok(tasksList);

    }


    @GetMapping(value = "/tasks/{taskId}", produces = "application/json")
    @ApiOperation(value = "get a task by id", notes = "Get the specific task associated with that task-id",response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 404, message = "No tasks found"),
            @ApiResponse(code = 403, message = "Not Authorized to perform this operation")
    })
    public ResponseEntity getTask(@ApiParam(value = "Task's Id") @PathVariable("taskId") Long taskId){
        return ResponseEntity.ok(taskService.findById(taskId));

    }


    @DeleteMapping(value = "tasks/{taskId}", produces = "application/json")
    @ApiOperation(value = "Delete a task", notes = "Delete the specific task associated with that task-id",response = Task.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted task"),
            @ApiResponse(code = 404, message = "Task not found"),
    })
    public ResponseEntity deleteTask(@ApiParam(value = "Task's Id") @PathVariable Long taskId){
        taskService.delete(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
