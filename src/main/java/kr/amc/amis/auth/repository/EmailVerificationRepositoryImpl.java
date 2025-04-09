package kr.amc.amis.auth.repository;

import java.util.Optional;
import kr.amc.amis.auth.application.interfaces.EmailVerificationRepository;
import kr.amc.amis.auth.domain.Email;
import kr.amc.amis.auth.repository.entity.EmailVerificationEntity;
import kr.amc.amis.auth.repository.jpa.JpaEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailText = email.getEmailText();
        EmailVerificationEntity emailVerificationEntity = jpaEmailVerificationRepository.findByEmail(
                        emailText)
                .orElseThrow(() -> new IllegalArgumentException("email is not exist"));

        if (emailVerificationEntity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다.");
        }

        if (!emailVerificationEntity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰 값이 유효하지 않습니다.");
        }

        emailVerificationEntity.verify();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        String emailText = email.getEmailText();
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(
                        emailText)
                .orElseThrow(() -> new IllegalArgumentException("email is not exist"));

        return entity.isVerified();
    }
}
