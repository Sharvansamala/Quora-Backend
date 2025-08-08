package com.sharvan.QuoraApp.adapter;

import com.sharvan.QuoraApp.dto.TagRequest;
import com.sharvan.QuoraApp.dto.TagResponse;
import com.sharvan.QuoraApp.models.Tag;

public class TagAdapter {
    public static Tag toTag(TagRequest request) {
        return Tag.builder()
                .name(request.getName())
                .build();
    }
    public static TagResponse toTagResponse(Tag tag) {
        return TagResponse.builder()
                .name(tag.getName())
                .build();
    }
}
