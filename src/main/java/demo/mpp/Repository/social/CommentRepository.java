package demo.mpp.Repository.social;

import demo.mpp.Entity.social.CommentEntity;
import demo.mpp.Entity.social.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    CommentEntity findByCommentIdEquals(Integer commentId);
    List<CommentEntity> findAllByPostIdEquals(Integer postId);
}
