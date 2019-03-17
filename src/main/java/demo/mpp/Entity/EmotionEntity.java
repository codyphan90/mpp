package demo.mpp.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emotion")
public class EmotionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "type")
    private String type;

    public EmotionEntity() {
    }

    public EmotionEntity(Integer postId, String userId) {
        this.postId = postId;
        this.userName = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
