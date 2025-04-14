package kr.amc.amis.acceptance.auth;

import static kr.amc.amis.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static kr.amc.amis.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import kr.amc.amis.acceptance.utils.AcceptanceTestTemplate;
import kr.amc.amis.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email1@email.com";

    @BeforeEach
    public void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password", "token");

        // when
        String token = requestLoginGetToken(dto);

        // then
        Assertions.assertNotNull(token);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenCodeNotZero() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wron password", "token");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        Assertions.assertEquals(400, code);
    }



}
