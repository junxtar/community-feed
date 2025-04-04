package kr.amc.amis.post.repository.post_queue;

import java.util.List;
import kr.amc.amis.post.repository.entity.post.PostEntity;
import kr.amc.amis.post.repository.jpa.JpaPostRepository;
import kr.amc.amis.user.repository.entity.UserEntity;
import kr.amc.amis.user.repository.jpa.JpaUserRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaPostRepository jpaPostRepository;
    private final UserQueueRedisRepository redisRepository;
//    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());
        redisRepository.publishPostToFollowingList(postEntity, followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(
                targetId);
        redisRepository.publishPostListToFollowerUserList(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        redisRepository.deleteDeleteFeed(userId, targetId);
    }
}
