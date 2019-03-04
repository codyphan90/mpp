package survey.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.demo.Entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    List<AnswerEntity> findAllByQuestionIdEquals(Integer questionId);
    AnswerEntity findByIdEquals(Integer answerId);
}
