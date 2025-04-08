package kr.amc.amis.auth.application.interfaces;

import kr.amc.amis.auth.domain.Email;
import org.springframework.stereotype.Repository;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
}
