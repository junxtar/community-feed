package kr.amc.amis.post.application.interfaces;

import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.comment.Comment;
import kr.amc.amis.user.domain.User;

public interface LikeRepository {

    boolean checkLike(Post post, User user);

    void like(Post post, User user);

    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);

    void like(Comment comment, User user);

    void unlike(Comment comment, User user);
}
