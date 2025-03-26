package kr.amc.amis.post.application.dto;

import kr.amc.amis.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {

}
