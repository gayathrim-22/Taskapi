package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;
}