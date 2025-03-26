package kr.amc.amis.post.domain;

import kr.amc.amis.common.domain.PositiveIntegerCounter;
import kr.amc.amis.post.domain.content.Content;
import kr.amc.amis.post.domain.content.PostContent;
import kr.amc.amis.post.domain.content.PostPublicationState;
import kr.amc.amis.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content postContent;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, Content content) {
        if (author == null) {
            throw new NullPointerException("author is null");
        }
        this.id = id;
        this.author = author;
        this.postContent = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new NullPointerException("author is null");
        }
        this.id = id;
        this.author = author;
        this.postContent = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author and post are the same");
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("user is not author");
        }
        this.state = state;
        postContent.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }
}
