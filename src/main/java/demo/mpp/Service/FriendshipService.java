package demo.mpp.Service;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.utils.FriendshipFunctions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FriendshipService {
    @Autowired
    protected FriendShipRepository friendshipRepository;

    public List<String> getListUsersNeedFriendship (List<UserEntity> userList, List<FriendShipEntity> friendshipList, String targetUserName) {
        return FriendshipFunctions.convertUserEntity2UserName.apply(FriendshipFunctions.collectNotFriendShip.apply(userList,FriendshipFunctions.collectUserFriendship.apply(friendshipList,targetUserName)));
    }

    public List<FriendShipEntity> getListFriends(List<FriendShipEntity> friendshipList, String targetUserName) {
        return FriendshipFunctions.collectUserFriendship.apply(friendshipList,targetUserName);
    }

    public List<FriendShipEntity> getFullFriendshipList() {
        return friendshipRepository.findAll();
    }
}
