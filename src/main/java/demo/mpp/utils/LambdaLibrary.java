package demo.mpp.utils;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.PostEntity;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
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
}
