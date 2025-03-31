package kr.amc.amis.post.repository.jpa;

import kr.amc.amis.post.repository.entity.like.LikeEntity;
import kr.amc.amis.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
