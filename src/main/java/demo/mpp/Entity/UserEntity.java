package demo.mpp.Entity;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "dob")
    private Date dateOfBirth;

	public UserEntity() {
	}
	
	public UserEntity(String userName, String password, String fullName, Date dateOfBirth) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.dateOfBirth= dateOfBirth;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
