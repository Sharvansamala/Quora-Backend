package com.sharvan.quoraapp.adapter;

import com.sharvan.quoraapp.dto.TagRequest;
import com.sharvan.quoraapp.dto.TagResponse;
import com.sharvan.quoraapp.models.Tag;

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
