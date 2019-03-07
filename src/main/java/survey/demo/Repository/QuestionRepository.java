package survey.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.demo.Entity.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllBySurveyIdEquals(Integer surveyId);
    QuestionEntity findByIdEquals(Integer questionId);
}
