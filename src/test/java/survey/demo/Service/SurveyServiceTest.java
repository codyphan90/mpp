package survey.demo.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Entity.MCAnswerEntity;
import survey.demo.Entity.OEAnswerEntity;
import survey.demo.Entity.QuestionEntity;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Repository.SurveyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SurveyServiceTest {
    SurveyEntity surveyEntity;

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepositoryMock;

    @Mock
    private QuestionService questionServiceMock;

    @Mock
    private AnswerService answerServiceMock;

    @Before
    public void setUp() {
        buildSurveyEntity();
    }

    @Test
    public void test__getSurveyById__returnSucess() {
        when(surveyRepositoryMock.findByIdEquals(any())).thenReturn(surveyEntity);

        SurveyEntity result = surveyService.getSurveyById(surveyEntity.getId());
        assertEquals(surveyEntity.getId(), result.getId());
    }

    @Test
    public void getAllSurvey() {
        List<SurveyEntity> list = new ArrayList<>(Arrays.asList(surveyEntity));
        when(surveyRepositoryMock.findAllByIsActive(any())).thenReturn(list);

        List<SurveyEntity> result = surveyService.getAllSurvey();
        assertEquals(result.get(0),list.get(0));
    }

    @Test
    public void getSurveyWithFullContent() {
        when(surveyRepositoryMock.findByIdEquals(any())).thenReturn(surveyEntity);
        when(questionServiceMock.getQuestionAndAnswerListBySurveyId(any())).thenReturn(new ArrayList<QuestionEntity>());

        SurveyEntity result = surveyService.getSurveyWithFullContent(surveyEntity.getId());
        assertEquals(surveyEntity.getId(), result.getId());
    }

    @Test
    public void test__createSurvey__returnSuccess() {
        when(surveyRepositoryMock.save(any())).thenReturn(surveyEntity);
        when(questionServiceMock.createQuestion(any())).thenReturn(new QuestionEntity());

        SurveyEntity result = surveyService.createSurvey(surveyEntity);
        assertEquals(surveyEntity.getId(), result.getId());
    }

    @Test
    public void submitSurvey() {
        doNothing().when(questionServiceMock).submitRating(any());
        doNothing().when(answerServiceMock).submitMCAnswer(any());

        when(answerServiceMock.createOEAnswer(any())).thenReturn(new OEAnswerEntity());

        Boolean result = surveyService.submitSurvey(surveyEntity);
        assertTrue(result);
    }

    @Test
    public void createSurveyFromCSV() {

    }

    @Test
    public void buildQuestionFromCSV() {

    }

    @Test
    public void buildMCAnswerFromCSV () {

    }
    private void buildSurveyEntity() {
        surveyEntity = new SurveyEntity();
        surveyEntity.setId(1);

        MCAnswerEntity mcAnswerEntity = new MCAnswerEntity();
        mcAnswerEntity.setSelected(true);
        OEAnswerEntity oeAnswerEntity = new OEAnswerEntity();

        QuestionEntity questionEntity1 = new QuestionEntity();
        questionEntity1.setType("mc");
        questionEntity1.setRating(3.4);
        questionEntity1.setMcAnswerEntityList(new ArrayList<>(Arrays.asList(mcAnswerEntity)));

        QuestionEntity questionEntity2 = new QuestionEntity();
        questionEntity2.setType("oe");
        questionEntity2.setRating(5.0);
        questionEntity2.setOeAnswerEntityList(new ArrayList<>(Arrays.asList(oeAnswerEntity)));

        surveyEntity.setQuestionEntityList(new ArrayList<>(Arrays.asList(questionEntity1, questionEntity2)));
    }
}
