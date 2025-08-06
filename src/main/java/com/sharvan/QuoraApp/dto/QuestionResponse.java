package com.sharvan.QuoraApp.dto;

import com.sharvan.QuoraApp.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private String id;
    private String title;

    private List<TagResponse> tags;
    private String content;
    private String createdAt;

}
