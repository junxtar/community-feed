package kr.amc.amis.post.ui;

import java.util.List;
import kr.amc.amis.post.repository.post_queue.UserPostQueueQueryRepository;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;
import kr.amc.amis.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(value = "userId") Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> contentResponse = userPostQueueQueryRepository.getContentResponse(
                userId, lastPostId);
        return Response.ok(contentResponse);
    }
}
