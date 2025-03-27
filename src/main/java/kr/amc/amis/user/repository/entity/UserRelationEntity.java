package kr.amc.amis.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import kr.amc.amis.common.repository.entity.TimeBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "commuity_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId;
    @Id
    private Long followerUserId;

}
