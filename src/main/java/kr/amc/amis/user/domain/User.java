package kr.amc.amis.user.domain;

import java.util.Objects;
import kr.amc.amis.common.domain.PositiveIntegerCounter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private UserInfo userInfo;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public User(String name, String profileImageUrl) {
        this(null, new UserInfo(name, profileImageUrl));
    }

    public String getUserName() {
        return userInfo.getName();
    }

    public String getProfileImageUrl() {
        return userInfo.getProfileImageUrl();
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("Can't follow self");
        }
        this.followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unFollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("Can't follow self");
        }
        this.followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
