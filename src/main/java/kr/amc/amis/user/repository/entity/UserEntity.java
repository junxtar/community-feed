package kr.amc.amis.user.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.amc.amis.common.domain.PositiveIntegerCounter;
import kr.amc.amis.common.repository.entity.TimeBaseEntity;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "community_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.profileImage = user.getProfileImageUrl();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(this.name, this.profileImage))
                .followerCount(new PositiveIntegerCounter(this.followerCount))
                .followingCount(new PositiveIntegerCounter(this.followingCount))
                .build();
    }
}
