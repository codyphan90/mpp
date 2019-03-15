package demo.mpp.Service.survey;

import demo.mpp.Repository.survey.MCAnswerRepository;
import demo.mpp.Repository.survey.OEAnswerRepository;
import demo.mpp.Repository.survey.QuestionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.mpp.Constant.QuestionType;
import demo.mpp.Entity.survey.MCAnswerEntity;
import demo.mpp.Entity.survey.OEAnswerEntity;
import demo.mpp.Entity.survey.QuestionEntity;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class QuestionService {
    private Logger logger = LogManager.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MCAnswerRepository answerRepository;

    @Autowired
    private OEAnswerRepository oeAnswerRepository;

    @Autowired
    private AnswerService answerService;


    public QuestionEntity getQuestionById(Integer id) {
        return questionRepository.findByIdEquals(id);
    }

    public List<QuestionEntity> getQuestionListBySurveyId(Integer surveyId) {
        return questionRepository.findAllBySurveyIdEquals(surveyId);
    }

    public List<QuestionEntity> getQuestionAndAnswerListBySurveyId(Integer surveyId) {
        List<QuestionEntity> questionEntityList = questionRepository.findAllBySurveyIdEquals(surveyId);

        questionEntityList.forEach(questionEntity -> {
            if (questionEntity.getType().toUpperCase().equals(QuestionType.MC.toString())) {
                List<MCAnswerEntity> answerEntityList = answerRepository.findAllByQuestionIdEquals(questionEntity.getId());
                questionEntity.setMcAnswerEntityList(answerEntityList);
            } else if (questionEntity.getType().toUpperCase().equals(QuestionType.OE.toString())) {
                List<OEAnswerEntity> answerEntityList = oeAnswerRepository.findAllByQuestionIdEquals(questionEntity.getId());
                questionEntity.setOeAnswerEntityList(answerEntityList);
            }
        });
        logger.info("Found [{}] questions of survey Id [{}]", questionEntityList.size(), surveyId);
        return questionEntityList;
    }

    @Transactional
    public QuestionEntity createQuestion(QuestionEntity questionEntity) {
        questionEntity = questionRepository.save(questionEntity);

        logger.info("Created question with id [{}]", questionEntity.getId());
        if (questionEntity.getType().toUpperCase().equals(QuestionType.MC.toString())) {
            for (MCAnswerEntity answerEntity : questionEntity.getMcAnswerEntityList()) {
                answerEntity.setQuestionId(questionEntity.getId());
                answerService.createMCAnswer(answerEntity);
            }
        } else if (questionEntity.getType().toUpperCase().equals(QuestionType.OE.toString())) {
            if (questionEntity.getOeAnswerEntityList()!=null) {
                for (OEAnswerEntity answerEntity : questionEntity.getOeAnswerEntityList()) {
                    answerEntity.setQuestionId(questionEntity.getId());
                    answerService.createOEAnswer(answerEntity);
                }
            }
        }
        return questionEntity;
    }

    public void submitRating(QuestionEntity questionEntity) {
        QuestionEntity questionEntityInDB = questionRepository.findByIdEquals(questionEntity.getId());
        if (questionEntityInDB != null) {
            Double rating = questionEntityInDB.getRating();
            if (rating == null) {
                rating = questionEntity.getRating();
            } else {
                rating = (rating + questionEntity.getRating()) / 2;
            }
            questionEntityInDB.setRating(rating);
            questionRepository.save(questionEntityInDB);
            logger.info("Question Id [{}] set rating to [{}]", questionEntityInDB.getId(), questionEntityInDB.getRating());
        }
    }
}
