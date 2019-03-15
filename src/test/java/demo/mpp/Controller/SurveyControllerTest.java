package demo.mpp.Controller;

import demo.mpp.Entity.survey.MCAnswerEntity;
import demo.mpp.Entity.survey.OEAnswerEntity;
import demo.mpp.Entity.survey.QuestionEntity;
import demo.mpp.Entity.survey.SurveyEntity;
import demo.mpp.Request.SurveyRequest;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.survey.SurveyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SurveyControllerTest {
    private SurveyEntity surveyEntity;
    private SurveyRequest surveyRequest;
    @InjectMocks
    SurveyController surveyController;

    @Mock
    SurveyService surveyServiceMock;

    @Before
    public void setUp() {
        buildSurveyEntity();
    }

    @Test
    public void test__getSurveyById__returnSurveyEntity() {
        when(surveyServiceMock.getSurveyWithFullContent(any())).thenReturn(new SurveyEntity());
        ResponseEntity responseEntity = surveyController.getSurveyById(1);
        assertNotNull(responseEntity);
    }

    @Test
    public void test__getSurveyById__returnExceptionMessage() {
        when(surveyServiceMock.getSurveyWithFullContent(any())).thenReturn(null);
        ResponseEntity responseEntity = surveyController.getSurveyById(1);
        assertEquals("Can not find surveyId " + 1, responseEntity.getExceptionMessage());
    }

    @Test
    public void getAllSurveyIDs_whenNotFound() {
        when(surveyServiceMock.getAllSurvey()).thenReturn(null);
        ResponseEntity responseEntity = surveyController.getAllSurveyIDs();
        assertEquals("Can not find any survey", responseEntity.getExceptionMessage());
    }

    @Test
    public void getAllSurveyIDs_whenSuccess() {
        List<SurveyEntity> list = new ArrayList<>(Arrays.asList(surveyEntity));
        when(surveyServiceMock.getAllSurvey()).thenReturn(list);

        ResponseEntity responseEntity = surveyController.getAllSurveyIDs();
        assertNotNull(responseEntity);
    }

    @Test
    public void createSurvey_whenFailed() {
        when(surveyServiceMock.createSurvey(any())).thenReturn(null);

        ResponseEntity responseEntity = surveyController.createSurvey(surveyRequest);
        assertEquals(responseEntity.getSuccess(),false);
    }

    @Test
    public void createSurvey_whenSuccess() {
        when(surveyServiceMock.createSurvey(any())).thenReturn(surveyEntity);

        ResponseEntity responseEntity = surveyController.createSurvey(surveyRequest);
        assertEquals(responseEntity.getSuccess(),true);
    }

    @Test
    public void submitSurvey() {
    }

    @Test
    public void uploadCSV() {
    }

    public void buildSurveyEntity() {
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
        surveyRequest = new SurveyRequest();
        surveyRequest.setSurveyEntity(surveyEntity);
    }
}
