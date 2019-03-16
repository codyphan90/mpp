package demo.mpp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "friend_ship")
public class FriendShipEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "flow_user_name")
    private String flowUserName;

    public FriendShipEntity() {
    }

    public FriendShipEntity(Integer userId, String flowUserName) {
        this.userId = userId;
        this.flowUserName = flowUserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFlowUserName() {
        return flowUserName;
    }

    public void setFlowUserName(String flowUserName) {
        this.flowUserName = flowUserName;
    }
}
