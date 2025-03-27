package kr.amc.amis.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.comment.Comment;
import kr.amc.amis.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_like")
@Getter
@NoArgsConstructor
public class LikeEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }
    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }

}
