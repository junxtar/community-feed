package kr.amc.amis.post.repository;

import java.util.List;
import kr.amc.amis.post.repository.entity.post.PostEntity;
import kr.amc.amis.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    @Override
    public void publishPostToFollowingList(PostEntity postEntity, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowerUserList(List<PostEntity> postEntityList, Long userId) {

    }

    @Override
    public void deleteDeleteFeed(Long userId, Long authorId) {

    }
}
