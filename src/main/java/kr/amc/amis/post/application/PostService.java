package kr.amc.amis.post.application;

import kr.amc.amis.post.application.dto.CreatePostRequestDto;
import kr.amc.amis.post.application.dto.LikeRequestDto;
import kr.amc.amis.post.application.dto.UpdatePostRequestDto;
import kr.amc.amis.post.application.interfaces.LikeRepository;
import kr.amc.amis.post.application.interfaces.PostRepository;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.post.domain.content.Content;
import kr.amc.amis.post.domain.content.PostContent;
import kr.amc.amis.user.application.UserService;
import kr.amc.amis.user.domain.User;

public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, UserService userService,
            LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("post not found"));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());

        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }
        post.like(user);
        likeRepository.like(post, user);
    }

    public void unLikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }

}
