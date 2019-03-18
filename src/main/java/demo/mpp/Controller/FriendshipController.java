package demo.mpp.Controller;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Request.FriendshipRequest;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.FriendshipService;
import demo.mpp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private UserService userService;

    // get list of users that the targeted user should make friend or follow
    @RequestMapping(value = "/strangers/{user_name}",method = RequestMethod.GET)
    public @ResponseBody List<UserEntity> getListFriendNeedMakeFriendFollow(@PathVariable("user_name") String useName) {
        List<UserEntity> userEntityList = userService.getFullUserList();
        List<FriendShipEntity> friendshipList = friendshipService.getFullFriendshipList();
        return friendshipService.getListUsersWhoNotFriend(userEntityList,friendshipList,useName);
    }

    //get list of friends of a user
    @RequestMapping(value = "/friends/{user_name}",method = RequestMethod.GET)
    public @ResponseBody List<FriendShipEntity> getListFriends(@PathVariable("user_name") String useName) {
        List<FriendShipEntity> friendshipList = friendshipService.getFullFriendshipList();
        return friendshipService.getListFriends(friendshipList,useName);
    }

    // Send friend/follow request
    @RequestMapping(value = "/friendship",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity makeFriendShip(@RequestBody FriendshipRequest request) {
        FriendShipEntity friendShipEntity = friendshipService.makeFriendShip(request.getFriendshipEntity());
        if (friendShipEntity.getId() != null) {
            return new ResponseEntity<>(friendShipEntity);
        } else {
            return new ResponseEntity<>(false, "Can not make friendship between " +
                    request.getFriendshipEntity().getUserName() + ", " + request.getFriendshipEntity().getUserName());
        }
    }

    @RequestMapping(value = "/friendship/pending/{user_name}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getFriendPending(@PathVariable("user_name") String userName) {
        List<FriendShipEntity> pendingList = friendshipService.getFriendPending(userName);
        if (pendingList != null) {
            return new ResponseEntity<>(pendingList);
        } else {
            return new ResponseEntity<>("Can not get friend pending list of " + userName);
        }
    }

    // accept friend request
    @RequestMapping(value = "/friendship/accept",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity setAcceptFriend(@RequestBody FriendshipRequest request) {
        String message = friendshipService.setAcceptFriend(request.getFriendshipEntity());
        if (message != null) {
            return new ResponseEntity<>(true, null, message);
        } else {
            return new ResponseEntity<>( "Can not make friendship between " +
                    request.getFriendshipEntity().getUserName() + ", " + request.getFriendshipEntity().getUserName());
        }
    }

}
