package demo.mpp.utils;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Entity.UserEntity;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {

    /* Post */
    public static final BiFunction<List<PostEntity>, List<String>, List<PostEntity>> GET_NEW_FEED = (allPost, followUserList)
            -> Optional.ofNullable(allPost)
            .orElseGet(Collections::emptyList)
            .stream()
            .filter(Objects::nonNull)
            .filter(post -> followUserList.contains(post.getUserName()))
            .sorted(Comparator.comparing(PostEntity::getCreatedDate).reversed())
            .collect(Collectors.toList());

    public static final Function<List<FriendShipEntity>, List<String>> GET_FOLLOW_USER = listFriendShip
            -> Optional.ofNullable(listFriendShip)
            .orElseGet(Collections::emptyList)
            .stream()
            .filter(Objects::nonNull)
            .filter(FriendShipEntity::getFollowing)
            .map(FriendShipEntity::getRelateUserName)
            .collect(Collectors.toList());

    public static final BiFunction<List<PostEntity>, String, List<PostEntity>> GET_NEWEST_POST = (allPost, userName)
            -> Optional.ofNullable(allPost)
            .orElseGet(Collections::emptyList)
            .stream()
            .filter(Objects::nonNull)
            .filter(post -> post.getUserName().equals(userName))
            .sorted(Comparator.comparing(PostEntity::getCreatedDate).reversed())
            .limit(1).collect(Collectors.toList());

    /* FriendShip */
    // Collect list of users who does not have friendship
    public static final BiFunction<List<UserEntity>, List<FriendShipEntity>, List<UserEntity>> GET_WHO_NOT_FRIEND = (listUser, listFriendship)
            -> listUser.stream()
            .filter(user -> !listFriendship.stream()
                    .map(FriendShipEntity::getUserName)
                    .collect(Collectors.toList())
                    .contains(user.getUserName()))
            .collect(Collectors.toList());

    // From list of Friendship from db, filter friends of 1 user
    public static final BiFunction<List<FriendShipEntity>, String, List<FriendShipEntity>> GET_FRIEND = (friendShipEntityList, targetUserName)
            -> friendShipEntityList.stream()
            .filter(frs -> frs.getUserName().equals(targetUserName) &&frs.getFriend())
            .collect(Collectors.toList());

    public static final BiFunction<List<FriendShipEntity>, String, Integer> GET_FOLLOWERS_COUNT = (friendShipEntityList, userName)
            -> (int) friendShipEntityList.stream()
            .filter(fs -> fs.getRelateUserName().equals(userName) && fs.getFollowing()).count();

    public static final BiFunction<List<FriendShipEntity>, String, Integer> GET_FOLLOWING_COUNT = (friendShipEntityList, userName)
            -> (int) friendShipEntityList.stream()
            .filter(fs -> fs.getUserName().equals(userName) && fs.getFollowing()).count();

    public static final BiFunction<List<FriendShipEntity>, String, Integer> GET_FRIEND_COUNT = (friendShipEntityList, userName)
            -> (int) friendShipEntityList.stream()
            .filter(fs -> fs.getUserName().equals(userName) && fs.getFriend()).count();

    public static final BiFunction<List<FriendShipEntity>, String, Integer> GET_PENDING_COUNT = (friendShipEntityList, userName)
            -> (int) friendShipEntityList.stream()
            .filter(fs -> fs.getRelateUserName().equals(userName) && fs.getStatus().equals("pending")).count();

    // Get Friend Pending List
    public static final BiFunction<List<FriendShipEntity>, String, List<FriendShipEntity>> GET_FRIEND_PENDING = (friendshipList, relateUserName)
            -> friendshipList.stream()
            .filter(fs -> relateUserName.equals(fs.getRelateUserName()) && fs.getStatus().equals("pending"))
            .collect(Collectors.toList());

}
