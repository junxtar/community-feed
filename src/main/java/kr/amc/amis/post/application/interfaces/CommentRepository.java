package kr.amc.amis.post.application.interfaces;

import java.util.Optional;
import kr.amc.amis.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
