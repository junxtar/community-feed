package kr.amc.amis.message.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_fcm_token")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FcmTokenEntity {

    @Id
    private Long userId;
    private String fcmToken;

}
