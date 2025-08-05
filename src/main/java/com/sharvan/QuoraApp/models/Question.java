package com.sharvan.QuoraApp.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Question {
    @Id
    private String id;

    @NotBlank(message = "Title content cannot be blank")
    @Size(min = 10,max = 100, message = "Title must be between 10 and 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 20, max = 500, message = "Content must be between 20 and 500 characters")
    private String content;

    private String authorId;


    private Tag tag;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
