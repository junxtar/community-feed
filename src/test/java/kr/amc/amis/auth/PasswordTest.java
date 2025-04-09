package kr.amc.amis.auth;

import static kr.amc.amis.auth.domain.Password.createEncryptedPassword;

import kr.amc.amis.auth.domain.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        // given
        String password = "password";
        Password encryptedPassword = createEncryptedPassword(password);
        // when, then
        Assertions.assertTrue(encryptedPassword.matchPassword(password));
    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnTrue() {
        // given
        String password = "password";
        String fakePassword = "password1";
        Password encryptedPassword = createEncryptedPassword(fakePassword);
        // when, then
        Assertions.assertFalse(encryptedPassword.matchPassword(password));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> createEncryptedPassword(password));
    }

}
