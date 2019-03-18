package demo.mpp.utils;

import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.UserEntity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FriendshipFunctions {

    //Convert from list of user entity to list of user name
    public static final Function<List<UserEntity>,List<String>> convertUserEntity2UserName = (userList) ->userList.stream()
            .map(user->user.getUserName())
            .collect(Collectors.toList());

    // Collect list of users who does not have friendship
    public static final BiFunction<List<UserEntity>,List<FriendShipEntity>,List<UserEntity>> collectNotFriendShip =(listUser, listFriendship)-> listUser.stream()
            .filter(user->!listFriendship.stream()
                    .map(frs->frs.getUserName())
                    .collect(Collectors.toList())
                    .contains(user.getUserName()))
            .collect(Collectors.toList());

    // From list of Friendship from db, filter friends/followings of 1 user
    public static final BiFunction<List<FriendShipEntity>, String, List<FriendShipEntity>> collectUserFriendship = (userFrsList,targetUserName) -> userFrsList
            .stream()
            .filter(frs -> frs.getUserName().equals(targetUserName))
            .collect(Collectors.toList());
}
