package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.comments.Comment;
import com.jspiders.taskapi.data.comments.CommentDTO;
import com.jspiders.taskapi.data.comments.CreateCommentRequest;
import com.jspiders.taskapi.data.comments.UpdateCommentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CommentService {
    ResponseEntity<Comment> createComment(CreateCommentRequest createCommentRequest);
    ResponseEntity<List<Comment>> getAllComments();
    ResponseEntity<CommentDTO> getCommentByID(Long commentId);
    ResponseEntity<String> updateComment(UpdateCommentRequest updateCommentRequest);
    ResponseEntity<String> deleteCommentByID(Long commentId);
}
