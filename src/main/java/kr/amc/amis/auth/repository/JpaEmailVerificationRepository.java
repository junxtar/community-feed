package kr.amc.amis.auth.repository;

import java.util.Optional;
import kr.amc.amis.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    Optional<EmailVerificationEntity> findByEmail(String email);
}
