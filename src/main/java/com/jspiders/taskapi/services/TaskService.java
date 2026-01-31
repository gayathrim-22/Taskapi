package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.tasks.CreateTaskRequest;
import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.UpdateTaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    ResponseEntity<Task> createTask(CreateTaskRequest createTaskRequest);
    ResponseEntity<List<Task>> getAllTasks();
    ResponseEntity<Task> getTaskByID(Long taskId);
    ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest);
    ResponseEntity<String> deleteTaskByID(Long taskId);
}