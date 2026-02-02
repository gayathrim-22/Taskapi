package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.comments.*;
import com.jspiders.taskapi.data.tasks.*;
import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.AppUserRepository;
import com.jspiders.taskapi.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ObjectMapper mapper;
    private final CommentRepository commentRepository;
    private final AppUserRepository appUserRepository;
    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<Comment> createComment(CreateCommentRequest createCommentRequest) {
        log.info("inside createTask {}",createCommentRequest);

        Task task = taskRepository.findById(createCommentRequest.getUserId()).orElseThrow();

        //validate the userId if not present Throw NoSuchElementFoundException
        AppUser appUser = appUserRepository.findById(createCommentRequest.getUserId()).orElseThrow();

        //Convert createTaskRequest to Task Entity
        Comment comment = mapper.convertValue(createCommentRequest, Comment.class);

        //set created and updated Dates
        comment.setCreatedAt(LocalDate.now());
        comment.setAppUser(appUser);
//        comment.setText("Added create operation");
        comment.setTask(task);

        //save the task to db
        Comment savedComment = commentRepository.save(comment);
        log.info("saved {}",savedComment);

        //return the response with savedTask
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments() {
        return null;
    }

    @Override
    public ResponseEntity<CommentDTO> getCommentByID(Long commentId) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateComment(UpdateCommentRequest updateCommentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCommentByID(Long commentId) {
        return null;
    }
}
