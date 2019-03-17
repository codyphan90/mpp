package demo.mpp.Service;

import demo.mpp.Entity.CommentEntity;
import demo.mpp.Entity.EmotionEntity;
import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Repository.CommentRepository;
import demo.mpp.Repository.EmotionRepository;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.Repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
    private List<PostEntity> postEntityList;
    private List<CommentEntity> commentEntityList;
    private List<EmotionEntity> emotionEntityList;
    List<FriendShipEntity> friendShipEntityList = new ArrayList<>();


    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock
    private EmotionRepository emotionRepositoryMock;

    @Mock
    private FriendShipRepository friendShipRepositoryMock;

    @Before
    public void setUp() throws Exception {
        setUpData();

        when(postRepositoryMock.findAllByUserNameEquals(any())).thenReturn(postEntityList);
        when(commentRepositoryMock.findAllByPostIdEquals(any())).thenReturn(commentEntityList);
        when(emotionRepositoryMock.findAllByPostIdEquals(any())).thenReturn(emotionEntityList);
    }

    @Test
    public void getPostListWithFullDetail() {

        List<PostEntity> result = postService.getPostListWithFullDetail("test");
        assertEquals(result.size(), 1);

    }

    @Test
    public void getNewFeed() {
        friendShipEntityList.add(new FriendShipEntity("test","test2", true, true, "success"));
        when(postRepositoryMock.findAll()).thenReturn(postEntityList);
        when(friendShipRepositoryMock.findAllByUserNameEquals(any())).thenReturn(friendShipEntityList);
        List<PostEntity> result = postService.getNewFeed("test");
        assertEquals(result.size(), 1);
        assertEquals(result.stream().map(PostEntity::getUserName).collect(Collectors.joining()), "test2");


    }

    @Test
    public void createPost() {
        when(postRepositoryMock.save(any())).thenReturn(new PostEntity());
        PostEntity postEntity = postService.createPost(new PostEntity());
        assertNotNull(postEntity);
        verify(postRepositoryMock, times(1)).save(any());
    }

    @Test
    public void createComment() {
        when(commentRepositoryMock.save(any())).thenReturn(new CommentEntity());
        CommentEntity commentEntity = postService.createComment(new CommentEntity());
        assertNotNull(commentEntity);
        verify(commentRepositoryMock, times(1)).save(any());
    }

    @Test
    public void createEmotion() {
        when(emotionRepositoryMock.save(any())).thenReturn(new EmotionEntity());
        EmotionEntity emotionEntity = postService.createEmotion(new EmotionEntity());
        assertNotNull(emotionEntity);
        verify(emotionRepositoryMock, times(1)).save(any());
    }

    private void setUpData() {
        commentEntityList = new ArrayList<>(Arrays.asList(
                new CommentEntity("this test", 1, "test", new Date())));

        emotionEntityList = new ArrayList<>(Arrays.asList(new EmotionEntity()));
        postEntityList = new ArrayList<>(Arrays.asList(
                new PostEntity("this is post", new Date(), "test2")));
    }
}
