package demo.mpp.Entity;
import javax.persistence.*;

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
	
	@Column(name = "is_admin")
	private Boolean isAdmin;
	
	public UserEntity() {
	}
	
	public UserEntity(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.isAdmin = false;
	}
	
	public UserEntity(String userName, String password, Boolean isAdmin) {
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
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
    
    public Boolean getIsAdmin () {
    	return this.isAdmin;
    }
    
    public void setIsAdmin (Boolean isAdmin) {
    	this.isAdmin=isAdmin;
    }
}
