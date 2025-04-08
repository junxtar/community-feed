package kr.amc.amis.auth.application.interfaces;

import kr.amc.amis.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSendRepository {
    void sendEmail(Email email, String token);
}
