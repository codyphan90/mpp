package survey.demo.Entity;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	
	public Integer getId() {
        return this.id;
    }

    public String getUserName() {
        return this.username;
    }


    public void setUserName(String userName) {
        this.username = userName;
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
