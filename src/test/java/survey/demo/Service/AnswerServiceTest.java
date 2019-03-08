package survey.demo.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Entity.MCAnswerEntity;
import survey.demo.Entity.OEAnswerEntity;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Repository.MCAnswerRepository;
import survey.demo.Repository.OEAnswerRepository;
import survey.demo.Repository.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {
    private MCAnswerEntity mcAnswerEntity;
    private OEAnswerEntity oeAnswerEntity;

    @InjectMocks
    private AnswerService answerService;

    @Mock
    private MCAnswerRepository mcAnswerRepositoryMock;

    @Mock
    private OEAnswerRepository oeAnswerRepositoryMock;

    @Before
    public void setUp() {
        buildAnswerEntity();
    }

    @Test
    public void getAnswerById() {
        when(mcAnswerRepositoryMock.findByIdEquals(any())).thenReturn(mcAnswerEntity);

        MCAnswerEntity result = answerService.getAnswerById(mcAnswerEntity.getId());
        assertEquals(mcAnswerEntity.getId(), result.getId());
    }

    @Test
    public void getAnswerListByQuestionId() {
    }

    @Test
    public void createMCAnswer() {
    }

    @Test
    public void submitMCAnswer() {
    }

    @Test
    public void createOEAnswer() {
    }

    @Test
    public void submitOEAnswer() {
    }

    public void buildAnswerEntity () {
        MCAnswerEntity mcAnswerEntity = new MCAnswerEntity();
        mcAnswerEntity.setId(1);
        mcAnswerEntity.setQuestionId(1);
        mcAnswerEntity.setSelected(true);
        OEAnswerEntity oeAnswerEntity = new OEAnswerEntity();
        oeAnswerEntity.setId(2);
        oeAnswerEntity.setQuestionId(2);
    }
}