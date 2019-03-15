package demo.mpp.Repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.survey.MCAnswerEntity;

import java.util.List;

public interface MCAnswerRepository extends JpaRepository<MCAnswerEntity, Integer> {
    List<MCAnswerEntity> findAllByQuestionIdEquals(Integer questionId);
    MCAnswerEntity findByIdEquals(Integer answerId);
}
