package demo.mpp.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;

	@Column(name = "full_name")
    private String fullName;

	@Column(name = "is_admin")
    private Boolean isAdmin;

	@OneToMany(mappedBy = "userId")
    private List<FriendShipEntity> friendShipEntityList;

	@OneToMany(mappedBy = "userId")
    private List<PostEntity> postEntityList;

	public UserEntity() {
	}
	
	public UserEntity(String userName, String password, String fullName) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

    public UserEntity(String userName, String password, String fullName, List<FriendShipEntity> friendShipEntityList, List<PostEntity> postEntityList) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.friendShipEntityList = friendShipEntityList;
        this.postEntityList = postEntityList;
    }

    public Integer getId() {
        return this.id;
    }
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<FriendShipEntity> getFriendShipEntityList() {
        return friendShipEntityList;
    }

    public void setFriendShipEntityList(List<FriendShipEntity> friendShipEntityList) {
        this.friendShipEntityList = friendShipEntityList;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }
}
