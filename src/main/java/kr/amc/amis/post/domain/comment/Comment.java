package kr.amc.amis.post.domain.comment;

import kr.amc.amis.common.domain.PositiveIntegerCounter;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.content.CommentContent;
import kr.amc.amis.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }
    public Comment(Long id, Post post, User author, CommentContent content) {
        if (author == null) {
            throw new NullPointerException("author is null");
        }
        if (content == null) {
            throw new NullPointerException("content is null");
        }
        if (post == null) {
            throw new NullPointerException("post is null");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User user, String updateContent) {
        if (!user.equals(author)) {
            throw new IllegalArgumentException("user is not author");
        }
        content.updateContent(updateContent);
    }
}
