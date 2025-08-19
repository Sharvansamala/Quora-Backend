package com.sharvan.QuoraApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDTO {
    private String id;
    private String targetId; // ID of the question or answer being liked
    private String targetType; // Type of the target, e.g., "answer" or "question"
    private boolean isLike; // true for like, false for dislike
    private String createdAt;
}
