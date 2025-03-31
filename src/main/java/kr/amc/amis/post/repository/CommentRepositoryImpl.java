package kr.amc.amis.post.repository;

import kr.amc.amis.post.application.interfaces.CommentRepository;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.comment.Comment;
import kr.amc.amis.post.repository.entity.comment.CommentEntity;
import kr.amc.amis.post.repository.jpa.JpaCommentRepository;
import kr.amc.amis.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        Post post = comment.getPost();
        if (comment.getId() != null) {
            jpaCommentRepository.updateComment(commentEntity);
            return commentEntity.toComment();
        }
        CommentEntity save = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(post.getId());
        return save.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow(null);
        return commentEntity.toComment();
    }
}
