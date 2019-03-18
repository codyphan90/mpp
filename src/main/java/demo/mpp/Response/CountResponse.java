package demo.mpp.Response;

public class CountResponse {
    private Integer friendCount;
    private Integer followingCount;
    private Integer followerCount;

    public CountResponse(Integer friendCount, Integer followingCount, Integer followerCount) {
        this.friendCount = friendCount;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
}
