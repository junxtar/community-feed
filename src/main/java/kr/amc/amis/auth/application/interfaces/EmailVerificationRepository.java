package kr.amc.amis.auth.application.interfaces;

import kr.amc.amis.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);
    boolean isEmailVerified(Email email);
}
