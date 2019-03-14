package demo.mpp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.mpp.Entity.SurveyEntity;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    SurveyEntity findByIdEquals(Integer id);
    List<SurveyEntity> findAllByIsActiveAndIsActive(Boolean True, Boolean False);
    List<SurveyEntity> findAllByIsActive(Boolean isActive);
}
