package com.sharvan.quoraapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDTO {

    private String id;
    private String content;
    private String createdAt;
    private String updatedAt;
}
