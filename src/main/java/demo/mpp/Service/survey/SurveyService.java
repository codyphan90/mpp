package demo.mpp.Service.survey;

import demo.mpp.Constant.QuestionType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import demo.mpp.Entity.survey.MCAnswerEntity;
import demo.mpp.Entity.survey.OEAnswerEntity;
import demo.mpp.Entity.survey.QuestionEntity;
import demo.mpp.Entity.survey.SurveyEntity;
import demo.mpp.Repository.survey.SurveyRepository;
import demo.mpp.Constant.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Boolean updateSurvey(SurveyEntity surveyEntity) {
        SurveyEntity savedSurveyEntity = surveyRepository.findByIdEquals(surveyEntity.getId());
        if (!surveyEntity.getName().equals(savedSurveyEntity.getName())) savedSurveyEntity.setName(surveyEntity.getName());
        for (QuestionEntity questionEntity: surveyEntity.getQuestionEntityList()) {
            for (QuestionEntity savedQuestionEntity: savedSurveyEntity.getQuestionEntityList()) {
                if (questionEntity.getId().equals(savedQuestionEntity.getId())) {
                    if (!questionEntity.getContent().equals(savedQuestionEntity.getContent())) {
                        savedQuestionEntity.setContent(questionEntity.getContent());
                    }

                }
            }
        }
        surveyRepository.save(savedSurveyEntity);
        return true;
    }

    @Transactional
    public Boolean submitSurvey(SurveyEntity surveyEntity) {
        surveyEntity.getQuestionEntityList().forEach(questionEntity -> {
            if (questionEntity.getType().toUpperCase().equals(QuestionType.MC.toString())) {
                if (questionEntity.getRating() != null) {
                    questionService.submitRating(questionEntity);
                }
                questionEntity.getMcAnswerEntityList().forEach(answerEntity -> {
                    if (answerEntity.getSelected() != null && answerEntity.getSelected()) {
                        answerService.submitMCAnswer(answerEntity);
                    }
                });
            } else if (questionEntity.getType().toUpperCase().equals(QuestionType.OE.toString())) {
                if (questionEntity.getOeAnswerEntityList()!= null) {
                    List<OEAnswerEntity> list = questionEntity.getOeAnswerEntityList();
                    for(OEAnswerEntity oeEntity:list) {
                        if (!StringUtils.isEmpty(oeEntity.getContent())) answerService.createOEAnswer(oeEntity);
                    }
                }
            }
        });
        return true;
    }

    public Boolean createSurveyFromCSV(MultipartFile file) throws IOException {
        CSVParser csvParser = CSVFormat.EXCEL.parse(new InputStreamReader(file.getInputStream()));
        SurveyEntity surveyEntity = new SurveyEntity(file.getOriginalFilename().replaceAll(".csv", ""), true);
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
        questionEntity.setType(csvRecord.get(1));
        if (questionEntity.getType().toUpperCase().equals(QuestionType.MC.toString())) {
            questionEntity.setMcAnswerEntityList(buildMCAnswerFromCSV(csvRecord));
        }
        return questionEntity;
    }

    private List<MCAnswerEntity> buildMCAnswerFromCSV(CSVRecord csvRecord) {
        MCAnswerEntity answerEntity1 = new MCAnswerEntity(csvRecord.get(2));
        MCAnswerEntity answerEntity2 = new MCAnswerEntity(csvRecord.get(3));
        MCAnswerEntity answerEntity3 = new MCAnswerEntity(csvRecord.get(4));
        MCAnswerEntity answerEntity4 = new MCAnswerEntity(csvRecord.get(5));

        List<MCAnswerEntity> answerEntityList = new ArrayList<>(
                Arrays.asList(answerEntity1, answerEntity2, answerEntity3, answerEntity4));
        if(!StringUtils.isEmpty(csvRecord.get(6))) {
            answerEntityList.get(Integer.parseInt(csvRecord.get(6))-1).setDefault(true);
        }
        return answerEntityList;
    }
}
