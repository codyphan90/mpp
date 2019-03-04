package survey.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.demo.Entity.SurveyEntity;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    SurveyEntity findByIdEquals(Integer id);
}
