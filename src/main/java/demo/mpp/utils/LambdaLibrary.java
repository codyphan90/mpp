package demo.mpp.utils;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Entity.UserEntity;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
    public static final BiFunction<List<PostEntity>, List<String>, List<PostEntity>> NEW_FEED = ( allPost, followUserList)
            -> allPost.stream()
            .filter(post -> followUserList.contains(post.getUserName()))
            .sorted(Comparator.comparing(PostEntity::getCreatedDate).reversed())
            .collect(Collectors.toList());

    public static final Function<List<FriendShipEntity>, List<String> > GET_FOLLOW_USER  = listFriendShip
            -> listFriendShip.stream()
            .filter(friendship -> friendship.getFollowing())
            .map(friendship -> friendship.getRelateUserName())
            .collect(Collectors.toList());
}
