package kr.amc.amis.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {

}
