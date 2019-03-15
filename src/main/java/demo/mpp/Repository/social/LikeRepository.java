package demo.mpp.Repository.social;

import demo.mpp.Entity.social.CommentEntity;
import demo.mpp.Entity.social.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByIdEquals(Integer likeId);
    Set<LikeEntity> findAllByPostIdEquals(Integer postId);
}
