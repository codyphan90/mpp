package survey.demo.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Response.ResponseEntity;
import survey.demo.Service.SurveyService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SurveyControllerTest {
    @InjectMocks
    SurveyController surveyController;

    @Mock
    SurveyService surveyServiceMock;

    @Before
    public void setUp() {

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
    public void getAllSurveyIDs() {
    }

    @Test
    public void createSurvey() {
    }

    @Test
    public void submitSurvey() {
    }

    @Test
    public void uploadCSV() {
    }
}
