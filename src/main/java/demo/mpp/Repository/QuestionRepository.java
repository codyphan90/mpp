package demo.mpp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllBySurveyIdEquals(Integer surveyId);
    QuestionEntity findByIdEquals(Integer questionId);
}
