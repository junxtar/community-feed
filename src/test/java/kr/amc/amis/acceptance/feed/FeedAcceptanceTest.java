package kr.amc.amis.acceptance.feed;

import static kr.amc.amis.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static kr.amc.amis.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static kr.amc.amis.acceptance.steps.LikeAcceptanceSteps.likePost;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import kr.amc.amis.acceptance.utils.AcceptanceTestTemplate;
import kr.amc.amis.post.application.dto.CreatePostRequestDto;
import kr.amc.amis.post.application.dto.LikeRequestDto;
import kr.amc.amis.post.domain.content.PostPublicationState;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;
    /**
     * User 1 --- follow --> User 2
     * User 1 --- follow --> User 3
     */
    @BeforeEach
    void setUp() {
        super.init();
        this.token = login("user1@email.com");

    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */

    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L,
                "user 1 can get this post", PostPublicationState.PUBLIC);
        Long postId = requestCreatePost(dto);

        // when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(token);

        // then
        assertEquals(1, result.size());
        assertEquals(postId, result.get(0).getId());
    }

    @Test
    @Disabled
    void givenUserHasFollowerAndCreatePostAndLikePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L,
                "user 1 can get this post", PostPublicationState.PUBLIC);
        Long postId = requestCreatePost(dto);
        likePost(new LikeRequestDto(postId, 1L));

        // when
        List<GetPostContentResponseDto> result = requestFeed(token);

        // then
        assertEquals(1, result.size());
        assertTrue(result.get(0).isLikedByMe());
    }
}
