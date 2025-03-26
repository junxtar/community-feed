package kr.amc.amis.post.application.dto;

import kr.amc.amis.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
