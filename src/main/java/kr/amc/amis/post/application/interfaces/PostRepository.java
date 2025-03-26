package kr.amc.amis.post.application.interfaces;

import java.util.Optional;
import kr.amc.amis.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);

}
