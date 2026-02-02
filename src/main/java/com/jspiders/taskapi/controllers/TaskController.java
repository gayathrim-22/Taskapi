package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.tasks.AssignTagToTaskResponse;
import com.jspiders.taskapi.data.tasks.CreateTaskRequest;
import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.TaskDTO;
import com.jspiders.taskapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest createTaskRequest)
    {
        log.info("inside createTask {}",createTaskRequest);
        return taskService.createTask(createTaskRequest);
    }

    @PostMapping("/{taskId}/tags/{tagId}")
    ResponseEntity<AssignTagToTaskResponse> assignTagToTask(@PathVariable Long taskId,
                                                            @PathVariable Long tagId){
        return taskService.addTagToTask(taskId,tagId);
    }

    @GetMapping("/{taskId}")
    ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId){
        log.info("getUserById()");
        ResponseEntity<TaskDTO> response = taskService.getTaskById(taskId);
        return response;
    }
}