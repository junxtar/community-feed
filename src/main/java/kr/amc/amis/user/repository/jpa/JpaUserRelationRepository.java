package kr.amc.amis.user.repository.jpa;

import java.util.List;
import kr.amc.amis.user.repository.entity.UserRelationEntity;
import kr.amc.amis.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends
        JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query(value = "SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followerUserId = :followerUserId")
    List<Long> findFollowers(Long followerUserId);
}
