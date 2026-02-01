package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.comments.Comment;
import com.jspiders.taskapi.data.comments.CreateCommentRequest;
import com.jspiders.taskapi.data.tasks.CreateTaskRequest;
import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    ResponseEntity<Comment> createTask(@RequestBody CreateCommentRequest createCommentRequest)
    {
        log.info("inside createTask {}",createCommentRequest);
        return commentService.createComment(createCommentRequest);
    }
}
