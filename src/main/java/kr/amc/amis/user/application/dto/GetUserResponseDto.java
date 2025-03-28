package kr.amc.amis.user.application.dto;

import kr.amc.amis.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, Integer followingCount, Integer followersCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getUserName(), user.getProfileImageUrl(), user.followingCount(), user.followerCount());
    }
}
