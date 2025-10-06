package com.sharvan.quoraapp.models;

import com.sharvan.quoraapp.models.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "likes")
public class Like {

    @Id
    private String id;

    private String targetId; // This can be the ID of  a question or an answer

    private TargetType targetType; // "question" or "answer"

    private boolean isLike; // true for like, false for dislike

    @CreatedDate
    private Instant createdAt;
}
