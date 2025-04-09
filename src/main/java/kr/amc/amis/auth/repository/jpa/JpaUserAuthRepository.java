package kr.amc.amis.auth.repository.jpa;

import kr.amc.amis.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

}
