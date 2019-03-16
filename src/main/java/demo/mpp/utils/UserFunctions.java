package demo.mpp.utils;

import demo.mpp.Constant.MessageConstant;
import demo.mpp.Entity.UserEntity;

import java.util.Optional;
import java.util.function.BiFunction;

public class UserFunctions {

    public static BiFunction<Integer, UserEntity, Optional<String>> validateUser = (numUser, user)-> numUser != 0 ? Optional.of(String.format(MessageConstant.DUPLICATE_EXCEPTION_MESSAGE, "User " + user.getUserName())) :Optional.empty();

}
