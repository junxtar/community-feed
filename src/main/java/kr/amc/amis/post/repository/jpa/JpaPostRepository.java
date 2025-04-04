package kr.amc.amis.post.repository.jpa;

import java.util.List;
import kr.amc.amis.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.state = :#{#postEntity.getState()}, "
            + "p.content = :#{#postEntity.getContent()}, "
            + "p.updDt = now() "
            + "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.likeCount = p.likeCount + :likeCount, "
            + "p.updDt = now() "
            + "WHERE p.id = :postId")
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1, "
            + "p.updDt = now() "
            + "WHERE p.id = :postEntityId")
    void increaseCommentCount(Long postEntityId);

    @Query("SELECT p FROM PostEntity p WHERE p.author.id = :authorId")
    List<PostEntity> findAllPostIdsByAuthorId(Long authorId);
}
