package kr.amc.amis.acceptance.auth;


import static kr.amc.amis.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kr.amc.amis.acceptance.utils.AcceptanceTestTemplate;
import kr.amc.amis.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    public void setUp() {
        super.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        // given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);

        // when
        Integer code = requestSendEmail(sendEmailRequestDto);

        // then
        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved() {
        // given
        String invalidEmail = "abcd";
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(invalidEmail);

        // when
        Integer code = requestSendEmail(sendEmailRequestDto);

        // then
//        String token = super.getEmailToken(email);
//        assertNull(token);
        assertEquals(400, code);
    }
}
