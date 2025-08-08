package com.sharvan.QuoraApp.adapter;

import com.sharvan.QuoraApp.dto.QuestionResponse;
import com.sharvan.QuoraApp.dto.TagRequest;
import com.sharvan.QuoraApp.dto.TagResponse;
import com.sharvan.QuoraApp.models.Question;
import com.sharvan.QuoraApp.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class QuestionAdapter {

    public static QuestionResponse toQuestionResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .tags(question.getTag().stream().map(TagAdapter::toTagResponse).toList())
                .createdAt(question.getCreatedAt().toString())
                .build();
    }

}
