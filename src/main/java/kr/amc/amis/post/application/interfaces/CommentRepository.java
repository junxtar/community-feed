package kr.amc.amis.post.application.interfaces;

import kr.amc.amis.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);
}
