package kr.amc.amis.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import kr.amc.amis.post.domain.content.PostContent;
import kr.amc.amis.user.domain.User;
import kr.amc.amis.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    private final Post post = new Post(1L, user1, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        post.like(user2);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeMe_thenThrownException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user1));
    }

    @Test
    void givenPostCreatedAndLike_whenUnLike_thenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }
}