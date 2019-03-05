package survey.demo.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.demo.Entity.QuestionEntity;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Repository.SurveyRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class SurveyService {
    private Logger logger = LogManager.getLogger(SurveyService.class);

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    public SurveyEntity getSurveyById(Integer id) {
        return surveyRepository.findByIdEquals(id);
    }

    public List<SurveyEntity> getAllSurvey() {
        return surveyRepository.findAllByIsActive(true);
    }

    public SurveyEntity getSurveyWithFullContent(Integer id) {
        SurveyEntity surveyEntity = surveyRepository.findByIdEquals(id);
        List<QuestionEntity> questionEntityList = questionService.getQuestionAndAnswerListBySurveyId(id);
        surveyEntity.setQuestionEntityList(questionEntityList);
        return surveyEntity;
    }

    @Transactional
    public SurveyEntity createSurvey(SurveyEntity surveyEntity) {
        if(surveyEntity.isActive() == null) surveyEntity.setActive(true);

        surveyEntity = surveyRepository.save(surveyEntity);
        logger.info("Created survey with id [{}]", surveyEntity.getId());

        for (QuestionEntity questionEntity: surveyEntity.getQuestionEntityList()) {
            questionEntity.setSurveyId(surveyEntity.getId());
            questionService.createQuestion(questionEntity);
        }
        return surveyEntity;
    }

    @Transactional
    public Boolean submitSurvey(SurveyEntity surveyEntity) {
        surveyEntity.getQuestionEntityList().forEach(questionEntity -> {
            questionService.submitRating(questionEntity);
            questionEntity.getAnswerEntityList().forEach(answerEntity -> {
                answerService.submitAnswer(answerEntity);
            });
        });
        return true;
    }
}
