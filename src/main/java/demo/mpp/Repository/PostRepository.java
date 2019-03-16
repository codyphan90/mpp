package demo.mpp.Repository;

import demo.mpp.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    PostEntity findByPostIdEquals(Integer postId);
    List<PostEntity> findAllByUserNameEquals(String userName);
}
