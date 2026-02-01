package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.comments.Comment;
import com.jspiders.taskapi.data.comments.CommentDTO;
import com.jspiders.taskapi.data.comments.CreateCommentRequest;
import com.jspiders.taskapi.data.tasks.TaskDTO;
import com.jspiders.taskapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest createCommentRequest)
    {
        log.info("inside createTask {}",createCommentRequest);
        return commentService.createComment(createCommentRequest);
    }

    @GetMapping("/{commentId}")
    ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId){
        log.info("getUserById()");
        ResponseEntity<CommentDTO> response = commentService.getCommentByID(commentId);
        return response;
    }
}
