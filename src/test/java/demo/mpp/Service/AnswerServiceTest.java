package demo.mpp.Service;

import demo.mpp.Repository.MCAnswerRepository;
import demo.mpp.Repository.OEAnswerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import demo.mpp.Entity.MCAnswerEntity;
import demo.mpp.Entity.OEAnswerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        List<MCAnswerEntity> list = new ArrayList<>(Arrays.asList(mcAnswerEntity));
        when(mcAnswerRepositoryMock.findAllByQuestionIdEquals(any())).thenReturn(list);

        List<MCAnswerEntity> result = answerService.getAnswerListByQuestionId(mcAnswerEntity.getQuestionId());
        assertEquals(mcAnswerEntity.getId(), result.get(0).getId());
    }

    @Test
    public void createMCAnswer() {
        when(mcAnswerRepositoryMock.save(any())).thenReturn(mcAnswerEntity);

        MCAnswerEntity result = answerService.createMCAnswer(mcAnswerEntity);
        assertEquals(mcAnswerEntity.getId(), result.getId());
    }

    @Test
    public void submitMCAnswer() {
        when(mcAnswerRepositoryMock.findByIdEquals(any())).thenReturn(mcAnswerEntity);
        when(mcAnswerRepositoryMock.save(any())).thenReturn(mcAnswerEntity);

        answerService.submitMCAnswer(mcAnswerEntity);
        verify(mcAnswerRepositoryMock,times(1)).save(any());
    }

    @Test
    public void createOEAnswer() {
        when(oeAnswerRepositoryMock.save(any())).thenReturn(oeAnswerEntity);

        OEAnswerEntity result = answerService.createOEAnswer(oeAnswerEntity);
        assertEquals(oeAnswerEntity.getId(), result.getId());
    }

    @Test
    public void submitOEAnswer() {
        when(oeAnswerRepositoryMock.findByIdEquals(any())).thenReturn(oeAnswerEntity);
        when(oeAnswerRepositoryMock.save(any())).thenReturn(oeAnswerEntity);

        answerService.submitOEAnswer(oeAnswerEntity);
        verify(oeAnswerRepositoryMock,times(1)).save(any());
    }

    public void buildAnswerEntity () {
        mcAnswerEntity = new MCAnswerEntity();
        mcAnswerEntity.setId(1);
        mcAnswerEntity.setQuestionId(1);
        mcAnswerEntity.setSelected(true);

        oeAnswerEntity = new OEAnswerEntity();
        oeAnswerEntity.setId(2);
        oeAnswerEntity.setQuestionId(2);
    }
}
