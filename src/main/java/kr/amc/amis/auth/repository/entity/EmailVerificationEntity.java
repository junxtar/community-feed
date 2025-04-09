package kr.amc.amis.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.amc.amis.common.repository.entity.TimeBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_email_verification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailVerificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String token;
    private boolean isVerified;

    public EmailVerificationEntity(String email, String token) {
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public boolean hasSameToken(String token) {
        return this.token.equals(token);
    }
}
