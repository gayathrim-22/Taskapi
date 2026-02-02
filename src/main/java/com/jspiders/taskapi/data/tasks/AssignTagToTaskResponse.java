package com.jspiders.taskapi.data.tasks;

import com.jspiders.taskapi.data.tags.Tags;
import lombok.Data;

import java.util.Set;

@Data
public class AssignTagToTaskResponse {
    private Long taskId;
    private String title;
    private Set<Tags> tags;
}
