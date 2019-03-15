package demo.mpp.Repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.survey.SurveyEntity;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    SurveyEntity findByIdEquals(Integer id);
    List<SurveyEntity> findAllByIsActiveAndIsActive(Boolean True, Boolean False);
    List<SurveyEntity> findAllByIsActive(Boolean isActive);
}
