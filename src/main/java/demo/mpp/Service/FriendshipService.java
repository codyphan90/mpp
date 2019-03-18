package demo.mpp.Service;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.utils.LambdaLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {
    @Autowired
    protected FriendShipRepository friendshipRepository;

    public List<String> getListUsersNeedFriendship (List<UserEntity> userList, List<FriendShipEntity> friendshipList, String targetUserName) {
         return LambdaLibrary.CONVERT_TO_USER_NAME
                .apply(LambdaLibrary.GET_WHO_NOT_FRIEND
                       .apply(userList, LambdaLibrary.GET_FRIEND_SHIP_OF_USER
                               .apply(friendshipList,targetUserName)));
    }

    public List<FriendShipEntity> getListFriends(List<FriendShipEntity> friendshipList, String targetUserName) {
        return LambdaLibrary.GET_FRIEND_SHIP_OF_USER.apply(friendshipList,targetUserName);
    }

    public List<FriendShipEntity> getFullFriendshipList() {
        return friendshipRepository.findAll();
    }

    public FriendShipEntity makeFriendShip(FriendShipEntity friendshipRequest) {
        FriendShipEntity friendShipEntity = friendshipRepository.findByUserNameEqualsAndAndRelateUserNameEquals(
                friendshipRequest.getUserName(), friendshipRequest.getRelateUserName());
        if (friendShipEntity != null) {
            friendShipEntity.setFriend(friendshipRequest.getFriend());
            friendShipEntity.setFollowing(friendshipRequest.getFollowing());
            friendShipEntity.setStatus(friendshipRequest.getStatus());
            return friendshipRepository.save(friendShipEntity);
        } else {
            return friendshipRepository.save(friendshipRequest);
        }
    }

    public List<FriendShipEntity> getFriendPending(String userName) {
        List<FriendShipEntity> allFriendShipList = friendshipRepository.findAll();
        return LambdaLibrary.GET_FRIEND_PENDING.apply(allFriendShipList, userName);
    }

    public String setAcceptFriend(FriendShipEntity friendshipRequest) {
        FriendShipEntity friendShipEntity = friendshipRepository.findByUserNameEqualsAndAndRelateUserNameEquals(
                friendshipRequest.getUserName(), friendshipRequest.getRelateUserName());

        if (friendShipEntity != null) {
            friendShipEntity.setFriend(friendshipRequest.getFriend());
            // if accept friend
            if (friendshipRequest.getFriend()) {
                friendShipEntity.setStatus("success");

                // make new relation ship for user who accept pending
                FriendShipEntity newFriendShip = new FriendShipEntity();
                newFriendShip.setUserName(friendshipRequest.getRelateUserName());
                newFriendShip.setRelateUserName(friendshipRequest.getUserName());
                newFriendShip.setFriend(true);
                newFriendShip.setFollowing(true);
                newFriendShip.setStatus("success");

                friendshipRepository.save(newFriendShip);
                friendshipRepository.save(friendShipEntity);
                return "You make friend with " + friendshipRequest.getUserName();
            } else {
                friendShipEntity.setStatus("fail");

                friendshipRepository.save(friendShipEntity);
                return "You denied to make friend with " + friendshipRequest.getUserName();
            }

        } else {
            return null;
        }
    }
}
