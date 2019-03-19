package demo.mpp.Service;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.Response.CountResponse;
import org.junit.Assert;
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
public class FriendshipServiceTest {
    private FriendShipEntity acceptRequest;
    private FriendShipEntity makeFriendRequest;
    private FriendShipEntity friendShipEntity;

    @InjectMocks
    private FriendshipService friendshipService;

    @Mock
    private FriendShipRepository friendShipRepositoryMock;

    @Before
    public void setUp() throws Exception {

        acceptRequest = new FriendShipEntity("test1", "test2", null,true, null);
        makeFriendRequest = new FriendShipEntity("test1", "test2", false, true,"pending");
        friendShipEntity = new FriendShipEntity("test1", "test2", false, true,"pending");
        when(friendShipRepositoryMock.findByUserNameEqualsAndAndRelateUserNameEquals(any(), any())).thenReturn(friendShipEntity);
        when(friendShipRepositoryMock.save(any())).thenReturn(new FriendShipEntity());
    }

    @Test
    public void getListUsersWhoNotFriend() {
        UserEntity user1 = new UserEntity("test1", "2r23r23r", "test1");
        UserEntity user2 = new UserEntity("test2", "2r23r23r", "test2");
        UserEntity user3 = new UserEntity("test3", "2r23r23r", "test3");
        UserEntity user4 = new UserEntity("test4", "2r23r23r", "test4");
        List<UserEntity> userEntityList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        List<FriendShipEntity> friendShipEntityList = new ArrayList<>(Arrays.asList(friendShipEntity));

        List<UserEntity> result = friendshipService.getListUsersWhoNotFriend(userEntityList, friendShipEntityList, "test1");

        assertEquals(3, result.size());
    }

    @Test
    public void getListFriends() {
        UserEntity user1 = new UserEntity("test1", "2r23r23r", "test1");
        UserEntity user2 = new UserEntity("test2", "2r23r23r", "test2");
        UserEntity user3 = new UserEntity("test3", "2r23r23r", "test3");
        UserEntity user4 = new UserEntity("test4", "2r23r23r", "test4");
        List<UserEntity> userEntityList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        friendShipEntity = new FriendShipEntity("test1", "test2", true, true,"success");
        List<FriendShipEntity> friendShipEntityList = new ArrayList<>(Arrays.asList(friendShipEntity));
        List<UserEntity> list=friendshipService.getListFriends(userEntityList,friendShipEntityList,"test1" );

        assertEquals(1,list.size());
    }

    @Test
    public void getListFollowers() {
        UserEntity user1 = new UserEntity("test1", "2r23r23r", "test1");
        UserEntity user2 = new UserEntity("test2", "2r23r23r", "test2");
        UserEntity user3 = new UserEntity("test3", "2r23r23r", "test3");
        UserEntity user4 = new UserEntity("test4", "2r23r23r", "test4");
        List<UserEntity> userEntityList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        friendShipEntity = new FriendShipEntity("test1", "test2", true, true,"success");
        List<FriendShipEntity> friendShipEntityList = new ArrayList<>(Arrays.asList(friendShipEntity));
        List<UserEntity> list=friendshipService.getListFollowers(userEntityList,friendShipEntityList,"test1" );

        assertEquals(1,list.size());
    }

    @Test
    public void getListFollowingUsers() {
        UserEntity user1 = new UserEntity("test1", "2r23r23r", "test1");
        UserEntity user2 = new UserEntity("test2", "2r23r23r", "test2");
        UserEntity user3 = new UserEntity("test3", "2r23r23r", "test3");
        UserEntity user4 = new UserEntity("test4", "2r23r23r", "test4");
        List<UserEntity> userEntityList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        friendShipEntity = new FriendShipEntity("test1", "test2", true, true,"success");
        List<FriendShipEntity> friendShipEntityList = new ArrayList<>(Arrays.asList(friendShipEntity));
        List<UserEntity> list=friendshipService.getListFollowingUsers(userEntityList,friendShipEntityList,"test2" );

        assertEquals(1,list.size());
    }

    @Test
    public void getFullFriendshipList() {
    }

    @Test
    public void makeFriendShip__hasFriendShipAlready() {
        when(friendShipRepositoryMock.findByUserNameEqualsAndAndRelateUserNameEquals(any(), any())).thenReturn(friendShipEntity);
        when(friendShipRepositoryMock.save(any())).thenReturn(friendShipEntity);

        FriendShipEntity result = friendshipService.changeFriendShip(makeFriendRequest);
        assertEquals(false, result.getFriend());
        assertEquals("pending", result.getStatus());
    }

    @Test
    public void makeFriendShip__doNotHaveRelationShipBefore() {
        when(friendShipRepositoryMock.findByUserNameEqualsAndAndRelateUserNameEquals(any(), any())).thenReturn(null);
        when(friendShipRepositoryMock.save(any())).thenReturn(friendShipEntity);

        FriendShipEntity result = friendshipService.changeFriendShip(makeFriendRequest);
        assertEquals(false, result.getFriend());
        assertEquals("pending", result.getStatus());

    }

    @Test
    public void getFriendPending() {
        FriendShipEntity friendShipEntityExclude = new FriendShipEntity("test3", "test2", true,true,"success");
        when(friendShipRepositoryMock.findAll()).thenReturn(new ArrayList<>(Arrays.asList(friendShipEntity, friendShipEntityExclude)));

        List<FriendShipEntity> result = friendshipService.getFriendPending("test2");
        assertEquals(1, result.size());
        assertEquals("test1", result.get(0).getUserName());
    }



    @Test
    public void setTrueAcceptFriend__returnSuccess() {
        acceptRequest.setFriend(true);

        String result = friendshipService.setAcceptFriend(acceptRequest);
        Assert.assertEquals(result,"You make friend with test1");
    }

    @Test
    public void setFalseAcceptFriend__returnSuccess() {
        acceptRequest.setFriend(false);

        String result = friendshipService.setAcceptFriend(acceptRequest);
        Assert.assertEquals(result,"You denied to make friend with test1");
    }

    @Test
    public void getFriendShipCount__returnCountResponse() {
        // friend: 2, following: 2, follower: 2, pending: 1
        FriendShipEntity fs1 = new FriendShipEntity("test1", "test2", true, true, "success");
        FriendShipEntity fs2 = new FriendShipEntity("test1", "test3", true, false, "success");
        FriendShipEntity fs3 = new FriendShipEntity("test1", "test4", false, true, "success");
        FriendShipEntity fs4 = new FriendShipEntity("test5", "test1", false, true, "success");
        FriendShipEntity fs5 = new FriendShipEntity("test6", "test1", false, true, "pending");

        when(friendShipRepositoryMock.findAll()).thenReturn(new ArrayList<>(Arrays.asList(fs1,fs2,fs3,fs4,fs5)));

        CountResponse result = friendshipService.getFriendShipCount("test1");
        assertEquals(2, (int)result.getFollowerCount());
        assertEquals(2, (int)result.getFriendCount());
        assertEquals(2, (int)result.getFollowingCount());
        assertEquals(1, (int)result.getPendingCount());

    }
}
