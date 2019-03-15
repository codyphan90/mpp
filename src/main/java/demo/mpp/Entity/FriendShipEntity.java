package demo.mpp.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "friend_ship")
public class FriendShipEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "flow_user_id")
    private Integer flowUserId;

    public FriendShipEntity() {
    }

    public FriendShipEntity(Integer userId, Integer flowUserId) {
        this.userId = userId;
        this.flowUserId = flowUserId;
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

    public Integer getFlowUserId() {
        return flowUserId;
    }

    public void setFlowUserId(Integer flowUserId) {
        this.flowUserId = flowUserId;
    }
}
