package kr.amc.amis.post.application;

import kr.amc.amis.post.application.dto.CreateCommentRequestDto;
import kr.amc.amis.post.application.dto.LikeRequestDto;
import kr.amc.amis.post.application.dto.UpdateCommentRequestDto;
import kr.amc.amis.post.application.interfaces.CommentRepository;
import kr.amc.amis.post.application.interfaces.LikeRepository;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.comment.Comment;
import kr.amc.amis.user.application.UserService;
import kr.amc.amis.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;

    public CommentService(CommentRepository commentRepository, UserService userService,
            PostService postService, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unLikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }

}
