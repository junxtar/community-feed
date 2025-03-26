package kr.amc.amis.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInfoTest {

    @Test
    void givenNameAndProfileImage_whenCreated_thenThrowNoting() {
        // given
        String name = "userName";
        String profileImage = "profileImage";

        // when, then
        Assertions.assertDoesNotThrow(() -> new UserInfo(name, profileImage));
    }

    @Test
    void givenBlankNameAndProfileImage_whenCreated_thenThrowError() {
        // given
        String name = "";
        String profileImage = "profileImage";

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImage));
    }

}
