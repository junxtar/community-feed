package kr.amc.amis.post.application.interfaces;

import kr.amc.amis.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

}
