package kr.amc.amis.post.ui;

import kr.amc.amis.common.idenpotency.Idempotent;
import kr.amc.amis.post.application.PostService;
import kr.amc.amis.post.application.dto.CreatePostRequestDto;
import kr.amc.amis.post.application.dto.LikeRequestDto;
import kr.amc.amis.post.application.dto.UpdatePostRequestDto;
import kr.amc.amis.post.domain.Post;
import kr.amc.amis.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);

        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
            @RequestBody UpdatePostRequestDto dto) {
        Post post = postService.updatePost(postId, dto);

        return Response.ok(post.getId());
    }

    @Idempotent
    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        postService.likePost(dto);

        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        postService.unLikePost(dto);

        return Response.ok(null);
    }

}
