package survey.demo.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Entity.*;
import survey.demo.Repository.QuestionRepository;
import survey.demo.Repository.SurveyRepository;
import survey.demo.Request.LoginRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    QuestionEntity mcQuestionEntity;
    QuestionEntity oeQuestionEntity;

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepositoryMock;

    @Mock
    private AnswerService answerServiceMock;

    @Before
    public void setUp() {
        buildQuestionEntity();
    }

    @Test
    public void getQuestionById() {
        when(questionRepositoryMock.findByIdEquals(any())).thenReturn(mcQuestionEntity);

        QuestionEntity result = questionService.getQuestionById(mcQuestionEntity.getId());
        assertEquals(mcQuestionEntity.getId(), result.getId());
    }

    @Test
    public void getQuestionListBySurveyId() {
        List<QuestionEntity> list = new ArrayList<>(Arrays.asList(mcQuestionEntity));
        when(questionRepositoryMock.findAllBySurveyIdEquals(any())).thenReturn(list);

        List<QuestionEntity> result = questionService.getQuestionListBySurveyId(mcQuestionEntity.getId());
        assertEquals(mcQuestionEntity.getId(), result.get(0).getId());
    }

    @Test
    public void getQuestionAndAnswerListBySurveyId() {
    }

    @Test
    public void createQuestion() {
    }

    @Test
    public void submitRating() {
    }

    public void buildQuestionEntity() {
        MCAnswerEntity mcAnswerEntity = new MCAnswerEntity();
        mcAnswerEntity.setSelected(true);
        OEAnswerEntity oeAnswerEntity = new OEAnswerEntity();

        QuestionEntity mcQuestionEntity = new QuestionEntity();
        mcQuestionEntity.setId(1);
        mcQuestionEntity.setType("mc");
        mcQuestionEntity.setRating(3.4);
        mcQuestionEntity.setMcAnswerEntityList(new ArrayList<>(Arrays.asList(mcAnswerEntity)));

        QuestionEntity oeQuestionEntity = new QuestionEntity();
        oeQuestionEntity.setId(2);
        oeQuestionEntity.setType("oe");
        oeQuestionEntity.setRating(5.0);
        oeQuestionEntity.setOeAnswerEntityList(new ArrayList<>(Arrays.asList(oeAnswerEntity)));
    }
}