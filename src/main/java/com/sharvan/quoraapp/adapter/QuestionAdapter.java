package com.sharvan.quoraapp.adapter;

import com.sharvan.quoraapp.dto.QuestionResponse;
import com.sharvan.quoraapp.models.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class QuestionAdapter {

    public static QuestionResponse toQuestionResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .viewCount(question.getViews())
                .tags(question.getTag().stream().map(TagAdapter::toTagResponse).toList())
                .createdAt(question.getCreatedAt().toString())
                .build();
    }

}
