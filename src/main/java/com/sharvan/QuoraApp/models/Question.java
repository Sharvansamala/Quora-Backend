package com.sharvan.QuoraApp.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String id;

    @NotBlank(message = "Question content cannot be blank")
    private String title;

    private String authorId;


}
