package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.tags.CreateTagRequest;
import com.jspiders.taskapi.services.impl.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagServiceImpl tagService;
    @PostMapping
    ResponseEntity<String> createNewTag(@RequestBody CreateTagRequest createTagRequest){
        return tagService.addTag(createTagRequest);
    }

}