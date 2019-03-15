package demo.mpp.Service;

import demo.mpp.Entity.survey.MCAnswerEntity;
import demo.mpp.Entity.survey.OEAnswerEntity;
import demo.mpp.Entity.survey.QuestionEntity;
import demo.mpp.Repository.survey.MCAnswerRepository;
import demo.mpp.Repository.survey.OEAnswerRepository;
import demo.mpp.Repository.survey.QuestionRepository;
import demo.mpp.Service.survey.AnswerService;
import demo.mpp.Service.survey.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import demo.mpp.Entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    QuestionEntity mcQuestionEntity;
    QuestionEntity oeQuestionEntity;
    MCAnswerEntity mcAnswerEntity;
    OEAnswerEntity oeAnswerEntity;

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepositoryMock;

    @Mock
    private AnswerService answerServiceMock;

    @Mock
    private MCAnswerRepository mcAnswerRepositoryMock;

    @Mock
    private OEAnswerRepository oeAnswerRepositoryMock;

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
    public void getQuestionAndAnswerListBySurveyId_MCQuestion() {
        List<QuestionEntity> list = new ArrayList<>(Arrays.asList(mcQuestionEntity));
        when(questionRepositoryMock.findAllBySurveyIdEquals(any())).thenReturn(list);
        when(mcAnswerRepositoryMock.findAllByQuestionIdEquals(any())).thenReturn(mcQuestionEntity.getMcAnswerEntityList());

        List<QuestionEntity> result = questionService.getQuestionAndAnswerListBySurveyId(mcQuestionEntity.getId());
        assertEquals(list.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void getQuestionAndAnswerListBySurveyId_OEQuestion() {
        List<QuestionEntity> list = new ArrayList<>(Arrays.asList(oeQuestionEntity));
        when(questionRepositoryMock.findAllBySurveyIdEquals(any())).thenReturn(list);
        when(oeAnswerRepositoryMock.findAllByQuestionIdEquals(any())).thenReturn(oeQuestionEntity.getOeAnswerEntityList());

        List<QuestionEntity> result = questionService.getQuestionAndAnswerListBySurveyId(oeQuestionEntity.getId());
        assertEquals(list.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void createQuestion_MCQuestion() {
        when(questionRepositoryMock.save(any())).thenReturn(mcQuestionEntity);
        when(answerServiceMock.createMCAnswer(mcAnswerEntity)).thenReturn(mcAnswerEntity);

        QuestionEntity result = questionService.createQuestion(mcQuestionEntity);
        assertEquals(mcQuestionEntity.getId(), result.getId());
    }

    @Test
    public void createQuestion_OEQuestion() {
        when(questionRepositoryMock.save(any())).thenReturn(oeQuestionEntity);
        when(answerServiceMock.createOEAnswer(oeAnswerEntity)).thenReturn(oeAnswerEntity);

        QuestionEntity result = questionService.createQuestion(oeQuestionEntity);
        assertEquals(oeQuestionEntity.getId(), result.getId());
    }

    @Test
    public void submitRating() {
        when(questionRepositoryMock.findByIdEquals(any())).thenReturn(mcQuestionEntity);
        when(questionRepositoryMock.save(any())).thenReturn(mcQuestionEntity);

        questionService.submitRating(mcQuestionEntity);
        verify(questionRepositoryMock,times(1)).save(any());
    }

    @Test
    public void submitRating_nullRating() {
        Double oldRating = mcQuestionEntity.getRating();
        when(questionRepositoryMock.findByIdEquals(any())).thenReturn(mcQuestionEntity);
        when(questionRepositoryMock.save(any())).thenReturn(mcQuestionEntity);
        mcQuestionEntity.setRating(null);

        questionService.submitRating(mcQuestionEntity);
        verify(questionRepositoryMock,times(1)).save(any());
    }

    public void buildQuestionEntity() {
        mcAnswerEntity = new MCAnswerEntity();
        mcAnswerEntity.setSelected(true);
        oeAnswerEntity = new OEAnswerEntity();

        mcQuestionEntity = new QuestionEntity();
        mcQuestionEntity.setId(1);
        mcQuestionEntity.setType("mc");
        mcQuestionEntity.setRating(3.4);
        mcQuestionEntity.setMcAnswerEntityList(new ArrayList<>(Arrays.asList(mcAnswerEntity)));

        oeQuestionEntity = new QuestionEntity();
        oeQuestionEntity.setId(2);
        oeQuestionEntity.setType("oe");
        oeQuestionEntity.setRating(5.0);
        oeQuestionEntity.setOeAnswerEntityList(new ArrayList<>(Arrays.asList(oeAnswerEntity)));
    }
}
