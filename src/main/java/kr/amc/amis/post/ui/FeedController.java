package kr.amc.amis.post.ui;

import java.util.List;
import kr.amc.amis.common.principal.AuthPrincipal;
import kr.amc.amis.common.principal.UserPrincipal;
import kr.amc.amis.post.repository.post_queue.UserPostQueueQueryRepository;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;
import kr.amc.amis.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId) {
        List<GetPostContentResponseDto> contentResponse = userPostQueueQueryRepository.getContentResponse(
                userPrincipal.getUserId(), lastPostId);
        return Response.ok(contentResponse);
    }
}
