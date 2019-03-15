package demo.mpp.Repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.survey.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllBySurveyIdEquals(Integer surveyId);
    QuestionEntity findByIdEquals(Integer questionId);
}
