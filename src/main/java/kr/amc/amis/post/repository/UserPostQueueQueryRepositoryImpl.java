package kr.amc.amis.post.repository;

import java.util.List;
import kr.amc.amis.post.repository.post_queue.UserPostQueueQueryRepository;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }
}
