package demo.mpp.Service;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.Response.CountResponse;
import demo.mpp.utils.LambdaLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    @Autowired
    protected FriendShipRepository friendshipRepository;

    public List<UserEntity> getListUsersWhoNotFriend (List<UserEntity> userList, List<FriendShipEntity> friendshipList, String targetUserName) {
        List<FriendShipEntity> friendship = LambdaLibrary.GET_FRIENDSHIP.apply(friendshipList,targetUserName);
        return LambdaLibrary.GET_WHO_NOT_FRIEND.apply(userList,friendship);
    }

    public List<UserEntity> getListFriends(List<UserEntity> userEntityList, List<FriendShipEntity> friendshipList, String targetUserName) {
        return LambdaLibrary.MAP_USERNAMES_2_USERENTITIES.apply(userEntityList,LambdaLibrary.GET_FRIEND.apply(friendshipList,targetUserName));
    }

    public List<UserEntity> getListFollowers(List<UserEntity> userEntityList, List<FriendShipEntity> friendshipList, String targetUserName) {
        return LambdaLibrary.MAP_USERNAMES_2_USERENTITIES.apply(userEntityList,LambdaLibrary.GET_FOLLOW_USER.apply(friendshipList.stream().filter(fr->fr.getUserName().equals(targetUserName)).collect(Collectors.toList())));
    }

    public CountResponse getFriendShipCount(String userName) {
        List<FriendShipEntity> allFriendShipList = friendshipRepository.findAll();
        Integer followingCount = LambdaLibrary.GET_FOLLOWING_COUNT.apply(allFriendShipList, userName);
        Integer followerCount = LambdaLibrary.GET_FOLLOWERS_COUNT.apply(allFriendShipList, userName);
        Integer friendCount = LambdaLibrary.GET_FRIEND_COUNT.apply(allFriendShipList, userName);
        Integer pendingCount = LambdaLibrary.GET_PENDING_COUNT.apply(allFriendShipList, userName);
        return new CountResponse(friendCount, followingCount, followerCount, pendingCount);
    }

    public List<FriendShipEntity> getFullFriendshipList() {
        return friendshipRepository.findAll();
    }

    public FriendShipEntity changeFriendShip(FriendShipEntity friendshipRequest) {
        FriendShipEntity friendShipEntity = friendshipRepository.findByUserNameEqualsAndAndRelateUserNameEquals(
                friendshipRequest.getUserName(), friendshipRequest.getRelateUserName());
        if (friendShipEntity != null) {
            if (friendshipRequest.getFriend() != null) friendShipEntity.setFriend(friendshipRequest.getFriend());
            if (friendshipRequest.getFollowing() != null) friendShipEntity.setFollowing(friendshipRequest.getFollowing());
            if (friendshipRequest.getStatus() != null) friendShipEntity.setStatus(friendshipRequest.getStatus());
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
