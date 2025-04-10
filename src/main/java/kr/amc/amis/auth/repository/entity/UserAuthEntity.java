package kr.amc.amis.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import kr.amc.amis.auth.domain.UserAuth;
import kr.amc.amis.common.repository.entity.TimeBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user_auth")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private Long userId;
    private String password;
    private String userRole;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.userId = userId;
        this.password = userAuth.getPassword();
        this.userRole = userAuth.getUserRole();
    }

    public UserAuth toUserAuth() {
        return new UserAuth(this.email, this.password, this.userRole, this.userId);
    }

    public void updateLastLoginDt() {
        this.lastLoginDt = LocalDateTime.now();
    }
}
