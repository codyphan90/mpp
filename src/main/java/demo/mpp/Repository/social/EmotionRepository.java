package demo.mpp.Repository.social;

import demo.mpp.Entity.social.EmotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Integer> {
    EmotionEntity findByIdEquals(Integer likeId);
    List<EmotionEntity> findAllByPostIdEquals(Integer postId);
}
