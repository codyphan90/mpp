package demo.mpp.Service;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.utils.FriendshipFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {
    @Autowired
    protected FriendShipRepository friendshipRepository;

    public List<String> getListUsersNeedFriendship (List<UserEntity> userList, List<FriendShipEntity> friendshipList, String targetUserName) {
         return FriendshipFunctions.convertUserEntity2UserName
                .apply(FriendshipFunctions.collectNotFriendShip
                       .apply(userList,FriendshipFunctions.collectUserFriendship
                               .apply(friendshipList,targetUserName)));
    }

    public List<FriendShipEntity> getListFriends(List<FriendShipEntity> friendshipList, String targetUserName) {
        return FriendshipFunctions.collectUserFriendship.apply(friendshipList,targetUserName);
    }

    public List<FriendShipEntity> getFullFriendshipList() {
        return friendshipRepository.findAll();
    }

    public String sendFriendshipRequest(FriendShipEntity friendshipEntity) {
        //Integer entityCheckDuplicate = friendshipRepository.

        //if (entityCheckDuplicate > 0)
        friendshipRepository.save(friendshipEntity);
        return null;
    }

    public String acceptFriendRequest(FriendShipEntity friendshipEntity) {
        //FriendShipEntity friendship = friendshipRepository.findAllByUserNameEquals(friendshipEntity.getUserName())
          //      .stream().collect(Collectors.toList());

        return null;
    }
}
