package demo.mpp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "friend_ship")
public class FriendShipEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "relate_user_name")
    private String relateUserName;

    @Column(name="friend")
    private Boolean friend;

    @Column(name="following")
    private Boolean following;

    @Column(name="status")
    private String status;

    public FriendShipEntity() {
    }

    public FriendShipEntity(String userName, String relateUserName) {
        this.userName = userName;
        this.relateUserName = relateUserName;
        this.friend=false;
        this.following=false;
        this.status=null;
    }


    public FriendShipEntity(String userName, String relateUserName, Boolean friend, Boolean following, String status) {
        this.userName = userName;
        this.relateUserName = relateUserName;
        this.friend = friend;
        this.following=following;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRelateUserName() {
        return relateUserName;
    }

    public void setRelateUserName(String relateUserName) {
        this.relateUserName = relateUserName;
    }

    public Boolean getFriend() {
        return friend;
    }

    public void setFriend(Boolean friend) {
        this.friend = friend;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
