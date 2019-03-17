package demo.mpp.Controller;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
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

    @RequestMapping(value = "/friendship/{user_name}",method = RequestMethod.GET)
    public @ResponseBody List<String> getListFriendNeedMakeFriendFollow(@PathVariable("user_name") String useName) {
        List<UserEntity> userEntityList = userService.getFullUserList();
        List<FriendShipEntity> friendshipList = friendshipService.getFullFriendshipList();
        return friendshipService.getListUsersNeedFriendship(userEntityList,friendshipList,useName);
    }
    @RequestMapping(value = "/friends/{user_name}",method = RequestMethod.GET)
    public @ResponseBody List<FriendShipEntity> getListFriends(@PathVariable("user_name") String useName) {
        List<FriendShipEntity> friendshipList = friendshipService.getFullFriendshipList();
        return friendshipService.getListFriends(friendshipList,useName);
    }
}
