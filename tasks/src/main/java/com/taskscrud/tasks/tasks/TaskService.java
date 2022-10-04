package com.taskscrud.tasks.tasks;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<List<Task>> getTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Task> getTask(Long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isPresent()) {
                return new ResponseEntity<>(task.get(), HttpStatus.OK);
            }

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Task> createTask(Task task) {
        try {
            Task createdTask = taskRepository.save(task);

            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Task> updateTask(Long id, Task task) {
        try {
            Optional<Task> taskFound = taskRepository.findById(id);

            if (taskFound.isPresent()) {
                Task _task = taskFound.get();
                _task.setTitle(task.getTitle());
                _task.setDescription(task.getDescription());
                _task.setDone(task.getDone());

                Task updatedTask = taskRepository.save(_task);

                return new ResponseEntity<>(updatedTask, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
