package kr.amc.amis.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        // when
        boolean actual = user1.equals(user2);

        // then
        assertFalse(actual);
    }

    @Test
    void givenTwoSameUser_whenEqual_thenReturnTrue() {
        // given
        User sameUser = new User(1L, userInfo);

        // when
        boolean isSame = sameUser.equals(user1);

        // then
        assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse() {
        // when
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        // then
        assertNotEquals(hashcode1, hashcode2);
    }

    @Test
    void givenTwoSameUser_whenHashCode_thenReturnTrue() {
        // given
        User sameUser = new User(1L, userInfo);

        // when
        int hashcode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        // then
        assertEquals(hashcode1, sameUserHashCode);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreasedUserCount() {
        // when
        user1.follow(user2);

        // then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUser1UnFollowUser2_thenDecreasedUserCount() {
        // given
        user1.follow(user2);

        // when
        user1.unFollow(user2);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenTwoUser_whenUser1UnFollowUser2_thenNotDecreasedUserCount() {
        // when
        user1.unFollow(user2);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
}