package survey.demo.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import survey.demo.Entity.UserEntity;

public class UserRequest {
	 @JsonProperty("userEntity")
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
