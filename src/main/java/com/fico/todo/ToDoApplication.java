package com.fico.todo;
import com.fico.todo.model.Task;
import com.fico.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class ToDoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        this.taskRepository.save(new Task( Long.valueOf(1), "mohit", "first task", Timestamp.valueOf("2021-02-20 23:59:59") , 1));
        this.taskRepository.save(new Task( Long.valueOf(1), "mohit2", "second task", Timestamp.valueOf("2021-02-20 23:59:59"), 2));
        this.taskRepository.save(new Task( Long.valueOf(2), "mohit", "first task", Timestamp.valueOf("2021-02-20 23:59:59"), 2));
        this.taskRepository.save(new Task( Long.valueOf(2), "mohit2", "second task", Timestamp.valueOf("2021-02-20 23:59:59"), 1));

    }
}
