package kr.amc.amis.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String username, String profileImageUrl) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.name = username;
        this.profileImageUrl = profileImageUrl;
    }
}
