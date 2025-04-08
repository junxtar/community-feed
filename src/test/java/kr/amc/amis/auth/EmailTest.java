package kr.amc.amis.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kr.amc.amis.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@b", "naver.com", "natty#@naver", "안녕@하세요.com"})
    void givenEmailIsValid_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab", "aa@naver.com", "natty@naver.com", "test@test.com"})
    void givenEmailIsValid_whenCreate_thenReturnEmail(String email) {
        // given

        // when
        Email emailValue = Email.createEmail(email);

        // then
        assertEquals(email, emailValue.getEmailText());
    }


}
