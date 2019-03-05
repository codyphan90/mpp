package survey.demo.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.demo.Entity.AnswerEntity;
import survey.demo.Repository.AnswerRepository;

import java.util.List;


@Service
public class AnswerService {
    private Logger logger = LogManager.getLogger(AnswerService.class);

    @Autowired
    private AnswerRepository answerRepository;


    public AnswerEntity getAnswerById(Integer id) {
        return answerRepository.findByIdEquals(id);
    }

    public List<AnswerEntity> getAnswerListByQuestionId(Integer questionId) {
        return answerRepository.findAllByQuestionIdEquals(questionId);
    }


    public AnswerEntity createAnswer(AnswerEntity answerEntity) {
        answerEntity = answerRepository.save(answerEntity);
        logger.info("Created answer with id [{}]", answerEntity.getId());
        return answerEntity;
    }

    public void submitAnswer(AnswerEntity answerEntity) {
        if(answerEntity.getSelected()) {
            AnswerEntity answerEntityInDB = answerRepository.findByIdEquals(answerEntity.getId());
            Integer count = answerEntityInDB.getCount();
            count++;
            answerEntityInDB.setCount(count);
            answerRepository.save(answerEntityInDB);
        }
    }
}
