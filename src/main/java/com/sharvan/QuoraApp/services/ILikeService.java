package com.sharvan.QuoraApp.services;

import com.sharvan.QuoraApp.dto.LikeRequestDTO;
import com.sharvan.QuoraApp.dto.LikeResponseDTO;
import reactor.core.publisher.Mono;

public interface ILikeService {
    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);

    Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<LikeResponseDTO> countDislikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<LikeResponseDTO> toggleLike(String targetId, String targetType, boolean isLike);
}
