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

    }

    @Test
    public void test__getSurveyById__returnSucess() {
    }

    @Test
    public void getAllSurvey() {
    }

    @Test
    public void getSurveyWithFullContent() {
    }

    @Test
    public void test__createSurvey__returnSuccess() {
        buildSurveyEntity();
        when(surveyRepositoryMock.save(any())).thenReturn(surveyEntity);
        when(questionServiceMock.createQuestion(any())).thenReturn(new QuestionEntity());

        SurveyEntity result = surveyService.createSurvey(surveyEntity);
        assertEquals(surveyEntity.getId(), result.getId());
    }

    @Test
    public void submitSurvey() {
        buildSurveyEntity();
        doNothing().when(questionServiceMock).submitRating(any());
        doNothing().when(answerServiceMock).submitMCAnswer(any());

        when(answerServiceMock.createOEAnswer(any())).thenReturn(new OEAnswerEntity());

        Boolean result = surveyService.submitSurvey(surveyEntity);
        assertTrue(result);
    }

    @Test
    public void createSurveyFromCSV() {
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
