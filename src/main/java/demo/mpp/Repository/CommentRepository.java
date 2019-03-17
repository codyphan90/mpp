package demo.mpp.Repository;

import demo.mpp.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    CommentEntity findByCommentIdEquals(Integer commentId);
    List<CommentEntity> findAllByPostIdEquals(Integer postId);
    List<CommentEntity> findAllByUserNameEquals(String userName);
}
