package survey.demo.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.demo.Entity.AnswerEntity;
import survey.demo.Entity.QuestionEntity;
import survey.demo.Repository.AnswerRepository;
import survey.demo.Repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class QuestionService {
    private Logger logger = LogManager.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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
            List<AnswerEntity> answerEntityList = answerRepository.findAllByQuestionIdEquals(questionEntity.getId());
            questionEntity.setAnswerEntityList(answerEntityList);
        });
        logger.info("Found [{}] questions of survey Id [{}]", questionEntityList.size(), surveyId);
        return questionEntityList;
    }

    @Transactional
    public QuestionEntity createQuestion(QuestionEntity questionEntity) {
        questionEntity = questionRepository.save(questionEntity);

        logger.info("Created question with id [{}]", questionEntity.getId());
        for (AnswerEntity answerEntity : questionEntity.getAnswerEntityList()) {
            answerEntity.setQuestionId(questionEntity.getId());
            answerService.createAnswer(answerEntity);
        }
        return questionEntity;
    }

    public void submitRating(QuestionEntity questionEntity) {
            QuestionEntity questionEntityInDB = questionRepository.findByIdEquals(questionEntity.getId());
            if (questionEntityInDB !=null) {
            Integer rating = questionEntityInDB.getRating();
            rating += questionEntity.getRating();
            questionEntityInDB.setRating(rating);
            questionRepository.save(questionEntityInDB);
            logger.info("Question Id [{}] set rating to [{}]", questionEntityInDB.getId(), questionEntityInDB.getRating());
            }
    }
}
