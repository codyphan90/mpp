package survey.demo.Service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import survey.demo.Entity.AnswerEntity;
import survey.demo.Entity.QuestionEntity;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Repository.SurveyRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum QuestionType {mc, oe};
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
        if (surveyEntity != null) {
        List<QuestionEntity> questionEntityList = questionService.getQuestionAndAnswerListBySurveyId(id);
        surveyEntity.setQuestionEntityList(questionEntityList);
        }
        return surveyEntity;
    }

    @Transactional
    public SurveyEntity createSurvey(SurveyEntity surveyEntity) {
        if(surveyEntity.isActive() == null) surveyEntity.setActive(true);
        surveyEntity = surveyRepository.save(surveyEntity);
        if(StringUtils.isEmpty(surveyEntity.getName())) surveyEntity.setName("Survey " + surveyEntity.getId());
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
            if (questionEntity.getRating() != null) {
                questionService.submitRating(questionEntity);
            }
            questionEntity.getAnswerEntityList().forEach(answerEntity -> {
                if (answerEntity.getSelected() != null && answerEntity.getSelected()) {
                    answerService.submitAnswer(answerEntity);
                }
            });
        });
        return true;
    }

    public Boolean createSurveyFromCSV(MultipartFile file) throws IOException {
        CSVParser csvParser = CSVFormat.EXCEL.parse(new InputStreamReader(file.getInputStream()));
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setQuestionEntityList(new ArrayList<>());

        for (CSVRecord csvRecord : csvParser) {
            surveyEntity.getQuestionEntityList().add(buildQuestionFromCSV(csvRecord));
        }
        SurveyEntity surveyInDB = createSurvey(surveyEntity);
        logger.info("Import CSV completely");
        return surveyInDB.getId() != null ;

    }

    private QuestionEntity buildQuestionFromCSV(CSVRecord csvRecord) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(csvRecord.get(0));
        questionEntity.setType(csvRecord.get(1).toString());
        if (questionEntity.getType().toLowerCase().equals(QuestionType.mc)) {
            questionEntity.setAnswerEntityList(buildMCAnswerFromCSV(csvRecord));
        }
        return questionEntity;
    }

    private List<AnswerEntity> buildMCAnswerFromCSV(CSVRecord csvRecord) {
        AnswerEntity answerEntity1 = new AnswerEntity(csvRecord.get(2));
        AnswerEntity answerEntity2 = new AnswerEntity(csvRecord.get(3));
        AnswerEntity answerEntity3 = new AnswerEntity(csvRecord.get(4));
        AnswerEntity answerEntity4 = new AnswerEntity(csvRecord.get(5));

        List<AnswerEntity> answerEntityList = new ArrayList<>(
                Arrays.asList(answerEntity1, answerEntity2, answerEntity3, answerEntity4));
        answerEntityList.get(Integer.parseInt(csvRecord.get(6))-1).setDefault(true);
        return answerEntityList;
    }
}
