package kr.amc.amis.post.repository;

import java.util.ArrayList;
import java.util.List;
import kr.amc.amis.post.repository.entity.post.PostEntity;
import kr.amc.amis.post.repository.post_queue.UserPostQueueQueryRepository;
import kr.amc.amis.post.ui.dto.GetPostContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostsByUserId(userId);
        ArrayList<GetPostContentResponseDto> result = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto build = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .build();
            result.add(build);
        }

        return result;
    }
}
