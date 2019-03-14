package demo.mpp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.OEAnswerEntity;

import java.util.List;

public interface OEAnswerRepository extends JpaRepository<OEAnswerEntity, Integer> {
    List<OEAnswerEntity> findAllByQuestionIdEquals(Integer questionId);
    OEAnswerEntity findByIdEquals(Integer answerId);
}

