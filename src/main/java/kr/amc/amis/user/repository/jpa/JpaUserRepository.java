package kr.amc.amis.user.repository.jpa;

import kr.amc.amis.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
