package kr.amc.amis.user.repository.jpa;

import kr.amc.amis.user.repository.entity.UserRelationEntity;
import kr.amc.amis.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
