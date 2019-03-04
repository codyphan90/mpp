package survey.demo.Entity;

public class UserEntity {
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public Integer getId() {
        return id;
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
    
    public boolean getIsAdmin () {
    	return this.isAdmin;
    }
    
    public void setIsAdmin (boolean isAdmin) {
    	this.isAdmin=isAdmin;
    }
}
