package kr.amc.amis.post.repository.post_queue;

import java.util.List;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
