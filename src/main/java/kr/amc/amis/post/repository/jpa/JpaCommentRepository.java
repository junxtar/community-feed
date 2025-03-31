package kr.amc.amis.post.repository.jpa;

import kr.amc.amis.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.content = :#{#commentEntity.getContent()},"
            + "c.updDt = now() "
            + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateComment(CommentEntity commentEntity);


    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.likeCount = c.likeCount + :likeCount, "
            + "c.updDt = now() "
            + "WHERE c.id = :CommentId")
    void updateLikeCount(Long CommentId, Integer likeCount);
}
