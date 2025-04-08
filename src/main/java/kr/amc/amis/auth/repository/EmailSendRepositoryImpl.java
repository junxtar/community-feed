package kr.amc.amis.auth.repository;

import kr.amc.amis.auth.application.interfaces.EmailSendRepository;
import kr.amc.amis.auth.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void sendEmail(Email email, String token) {

    }
}
