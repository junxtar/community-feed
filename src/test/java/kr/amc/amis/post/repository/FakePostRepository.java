package kr.amc.amis.post.repository;

import java.util.HashMap;
import java.util.Map;
import kr.amc.amis.post.application.interfaces.PostRepository;
import kr.amc.amis.post.domain.Post;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> posts = new HashMap<>();

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return null;
    }
}
