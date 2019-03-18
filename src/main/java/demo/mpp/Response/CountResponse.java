package demo.mpp.Response;

public class CountResponse {
    private Integer friendCount;
    private Integer followingCount;
    private Integer followerCount;
    private Integer pendingCount;

    public CountResponse(Integer friendCount, Integer followingCount, Integer followerCount, Integer pendingCount) {
        this.friendCount = friendCount;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
        this.pendingCount = pendingCount;
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

    public Integer getPendingCount() {
        return pendingCount;
    }
}
