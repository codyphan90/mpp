package demo.mpp.Service.survey;

import demo.mpp.Repository.survey.MCAnswerRepository;
import demo.mpp.Repository.survey.OEAnswerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.mpp.Entity.survey.MCAnswerEntity;
import demo.mpp.Entity.survey.OEAnswerEntity;

import java.util.List;


@Service
public class AnswerService {
    private Logger logger = LogManager.getLogger(AnswerService.class);

    @Autowired
    private MCAnswerRepository answerRepository;

    @Autowired
    private OEAnswerRepository oeAnswerRepository;

    public MCAnswerEntity getAnswerById(Integer id) {
        return answerRepository.findByIdEquals(id);
    }

    public List<MCAnswerEntity> getAnswerListByQuestionId(Integer questionId) {
        return answerRepository.findAllByQuestionIdEquals(questionId);
    }

    public MCAnswerEntity createMCAnswer(MCAnswerEntity answerEntity) {
        if (answerEntity.getCount() == null) answerEntity.setCount(0);
        answerEntity = answerRepository.save(answerEntity);
        logger.info("Created mcAnswer with id [{}]", answerEntity.getId());
        return answerEntity;
    }

    public void submitMCAnswer(MCAnswerEntity answerEntity) {
            MCAnswerEntity answerEntityInDB = answerRepository.findByIdEquals(answerEntity.getId());
            Integer count = answerEntityInDB.getCount();
            if(count == null) count = 0;
            count++;
            answerEntityInDB.setCount(count);
            answerRepository.save(answerEntityInDB);
            logger.info("Answer Id [{}] set count to [{}]", answerEntityInDB.getId(), answerEntityInDB.getCount());
    }

    public OEAnswerEntity createOEAnswer(OEAnswerEntity answerEntity) {
        answerEntity = oeAnswerRepository.save(answerEntity);
        logger.info("Created oeAnswer with id [{}]", answerEntity.getId());
        return answerEntity;
    }

    public void submitOEAnswer(OEAnswerEntity answerEntity) {
        OEAnswerEntity answerEntityInDB = oeAnswerRepository.findByIdEquals(answerEntity.getId());
        String content = answerEntity.getContent();
        answerEntityInDB.setContent(content);
        oeAnswerRepository.save(answerEntityInDB);
        logger.info("Answer Id [{}] set content to [{}]", answerEntityInDB.getId(), answerEntityInDB.getContent());
    }
}
