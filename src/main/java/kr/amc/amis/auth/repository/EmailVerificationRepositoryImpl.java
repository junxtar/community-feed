package kr.amc.amis.auth.repository;

import java.util.Optional;
import kr.amc.amis.auth.application.interfaces.EmailVerificationRepository;
import kr.amc.amis.auth.domain.Email;
import kr.amc.amis.auth.repository.entity.EmailVerificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void createEmailVerification(Email email, String token) {
        String emailText = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(
                emailText);

        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if (emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("이미 인증된 이메일입니다.");
            }
            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailText,
                token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }
}
