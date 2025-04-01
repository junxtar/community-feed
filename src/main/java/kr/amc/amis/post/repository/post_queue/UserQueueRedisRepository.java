package kr.amc.amis.post.repository.post_queue;

import java.util.List;
import kr.amc.amis.post.repository.entity.post.PostEntity;


public interface UserQueueRedisRepository {

    void publishPostToFollowingList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowerUserList(List<PostEntity> postEntityList, Long userId);
    void deleteDeleteFeed(Long userId, Long authorId);
}
