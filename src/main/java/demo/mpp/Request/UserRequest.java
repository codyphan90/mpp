package demo.mpp.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import demo.mpp.Entity.UserEntity;

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
