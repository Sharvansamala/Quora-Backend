package com.sharvan.QuoraApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "tag")
public class Tag {

    private String id;
    private String name;

    private Question question;
}
