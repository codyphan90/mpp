package demo.mpp.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.mpp.Entity.FriendShipEntity;

public class FriendshipRequest {
    @JsonProperty("friendshipEntity")
    private FriendShipEntity friendshipEntity;

    public FriendShipEntity getFriendshipEntity() {
        return friendshipEntity;
    }

    public void setFriendshipEntity(FriendShipEntity friendshipEntity) {
        this.friendshipEntity = friendshipEntity;
    }
}
