package kr.amc.amis.user.repository.jpa;

import java.util.List;
import kr.amc.amis.user.application.dto.GetUserListResponseDto;
import kr.amc.amis.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new kr.amc.amis.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)"
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollwingUserList(Long userId);

    @Query(value = "SELECT new kr.amc.amis.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)"
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto>  getFollwerUserList(Long userId);

}
