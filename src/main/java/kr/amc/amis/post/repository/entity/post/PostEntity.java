package kr.amc.amis.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.amc.amis.common.domain.PositiveIntegerCounter;
import kr.amc.amis.common.repository.entity.TimeBaseEntity;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.content.PostContent;
import kr.amc.amis.post.domain.content.PostPublicationState;
import kr.amc.amis.user.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authorId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;
    private Integer likeCount;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
        this.state = post.getState();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .postContent(new PostContent(content))
                .likeCount(new PositiveIntegerCounter(likeCount))
                .state(state)
                .build();
    }
}
