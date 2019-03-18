package demo.mpp.Controller;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Request.FriendshipRequest;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.FriendshipService;
import demo.mpp.Service.UserService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipControllerTest {

    private FriendShipEntity friendShipEntity;

    @InjectMocks
    private FriendshipController friendshipController;

    @Mock
    private FriendshipService friendshipServiceMock;

    @Mock
    private UserService userServiceMock;



    @Before
    public void setUp() throws Exception {

        friendShipEntity = new FriendShipEntity("test1", "test2", false, true,"pending");
    }

    @Test
    public void getListFriendNeedMakeFriendFollow() {
    }

    @Test
    public void getListFriends() {
    }

    @Test
    public void makeFriendShip__returnSuccess() {
        FriendshipRequest friendshipRequest = new FriendshipRequest();
        friendshipRequest.setFriendshipEntity(friendShipEntity);
        when(friendshipServiceMock.changeFriendShip(any())).thenReturn(friendShipEntity);

        ResponseEntity result = friendshipController.changeFriendShip(friendshipRequest);
        assertTrue(result.getSuccess());
    }

    @Test
    public void makeFriendShip__returnFail() {

        FriendshipRequest friendshipRequest = new FriendshipRequest();
        friendshipRequest.setFriendshipEntity(friendShipEntity);

        when(friendshipServiceMock.changeFriendShip(any())).thenReturn(null);

        ResponseEntity  result = friendshipController.changeFriendShip(friendshipRequest);
        assertFalse(result.getSuccess());
    }

    @Test
    public void getFriendPending__returnSuccess() {
        List<FriendShipEntity> pendingList = new ArrayList<>(Arrays.asList(friendShipEntity));
        when(friendshipServiceMock.getFriendPending(any())).thenReturn(pendingList);

        ResponseEntity result = friendshipController.getFriendPending("test1");
        assertTrue(result.getSuccess());

    }

    @Test
    public void getFriendPending__returnFail() {
        when(friendshipServiceMock.getFriendPending(any())).thenReturn(null);

        ResponseEntity result = friendshipController.getFriendPending("test1");
        assertEquals("Can not get friend pending list of test1", result.getExceptionMessage());

    }

    @Test
    public void setAcceptFriend__returnSuccess() {
        when(friendshipServiceMock.setAcceptFriend(any())).thenReturn("success");
        ResponseEntity result = friendshipController.setAcceptFriend(new FriendshipRequest());
        assertEquals("success",result.getDataResponse());

    }

    @Test
    public void setAcceptFriend__returnFail() {
        when(friendshipServiceMock.setAcceptFriend(any())).thenReturn(null);
        FriendshipRequest friendshipRequest = new FriendshipRequest();
        friendshipRequest.setFriendshipEntity(friendShipEntity);
        ResponseEntity result = friendshipController.setAcceptFriend(friendshipRequest);
        assertFalse(result.getSuccess());

    }
}
