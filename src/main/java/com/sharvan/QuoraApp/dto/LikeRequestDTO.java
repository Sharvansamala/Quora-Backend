package com.sharvan.QuoraApp.dto;

import com.sharvan.QuoraApp.models.enums.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDTO {
    @NotBlank(message = "Target ID cannot be blank")
    private String targetId; // ID of the answer or question being liked
    @NotBlank(message = "Target type cannot be blank")
    private TargetType targetType; // Type of the target, e.g., "answer" or
    @NotNull(message = "Like status cannot be null")
    private boolean isLike; // true for like, false for dislike
}
