package demo.mpp.Controller;

import demo.mpp.Entity.CommentEntity;
import demo.mpp.Entity.EmotionEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.PostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocialControllerTest {

    @InjectMocks
    private SocialController socialController;

    @Mock
    private PostService postServiceMock;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getPostsByUserId__returnSuccess() {
        when(postServiceMock.getPostListWithFullDetail(any())).thenReturn(new ArrayList<>(Arrays.asList(new PostEntity())));
        ResponseEntity response = socialController.getPostsByUserName("test");
        assertNotNull(response);
        assertEquals(response.success, true);

    }

    @Test
    public void getPostsByUserName__returnFail() {
        when(postServiceMock.getPostListWithFullDetail(any())).thenReturn(null);

        ResponseEntity response = socialController.getPostsByUserName("test");
        assertNotNull(response);
        assertEquals(response.success, false);

    }

    @Test
    public void createPost__returnSuccess() {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(1);
        when(postServiceMock.createPost(any())).thenReturn(postEntity);

        ResponseEntity response = socialController.createPost(new PostEntity());
        assertNotNull(response);
        assertEquals(response.success, true);

    }

    @Test
    public void createPost__returnFail() {
        when(postServiceMock.createPost(any())).thenReturn(new PostEntity());

        ResponseEntity response = socialController.createPost(new PostEntity());
        assertNotNull(response);
        assertEquals(response.success, false);

    }

    @Test
    public void createComment__returnSuccess() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPostId(1);
        when(postServiceMock.createComment(any())).thenReturn(commentEntity);

        ResponseEntity response = socialController.createComment(new CommentEntity());
        assertNotNull(response);
        assertEquals(response.success, true);

    }

    @Test
    public void createComment__returnFail() {
        when(postServiceMock.createComment(any())).thenReturn(new CommentEntity());

        ResponseEntity response = socialController.createComment(new CommentEntity());
        assertNotNull(response);
        assertEquals(response.success, false);


    }

    @Test
    public void createReaction__returnSuccess() {
        EmotionEntity emotionEntity = new EmotionEntity();
        emotionEntity.setPostId(1);
        when(postServiceMock.createEmotion(any())).thenReturn(emotionEntity);

        ResponseEntity response = socialController.createReaction(new EmotionEntity());
        assertNotNull(response);
        assertEquals(response.success, true);
    }

    @Test
    public void createReaction__returnFail() {
        when(postServiceMock.createEmotion(any())).thenReturn(new EmotionEntity());

        ResponseEntity response = socialController.createReaction(new EmotionEntity());
        assertNotNull(response);
        assertEquals(response.success, false);
    }



    @Test
    public void getNewFeed__returnSuccess() {
        when(postServiceMock.getNewFeed(any())).thenReturn(new ArrayList<>(Arrays.asList(new PostEntity())));

        ResponseEntity response = socialController.getNewFeed("test");
        assertNotNull(response);
        assertEquals(response.success, true);
    }

    @Test
    public void getNewFeed__returnFail() {
        when(postServiceMock.getNewFeed(any())).thenReturn(null);

        ResponseEntity response = socialController.getNewFeed("test");
        assertNotNull(response);
        assertEquals(response.success, false);
    }

}
