package kr.amc.amis.auth.application;

import kr.amc.amis.auth.application.dto.SendEmailRequestDto;
import kr.amc.amis.auth.application.interfaces.EmailSendRepository;
import kr.amc.amis.auth.application.interfaces.EmailVerificationRepository;
import kr.amc.amis.auth.domain.Email;
import kr.amc.amis.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;


    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
    }
}
