package survey.demo.SurveyService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Repository.SurveyRepository;


@Service
public class SurveyService {
    private Logger logger = LogManager.getLogger(SurveyService.class);

    @Autowired
    private SurveyRepository surveyRepository;

    public SurveyEntity getSurveyById(Integer id) {
        return surveyRepository.findByIdEquals(id);
    }

    public SurveyEntity createSurvey(String name) {
        SurveyEntity surveyEntity = new SurveyEntity(name, true);
        surveyEntity = surveyRepository.save(surveyEntity);
        logger.info("Created survey with id [{}]", surveyEntity.getId());
        return surveyEntity;
    }
}
