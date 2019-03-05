package survey.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import survey.demo.Entity.UserEntity;
import survey.demo.Repository.UserRepository;
import survey.demo.Request.*;
import survey.demo.Constant.*;

public class UserService {
	@Autowired
    protected UserRepository usersRepository;
	
	public UserEntity createUser(UserEntity userEntity) {
		userEntity.setPassword(userEntity.getPassword());
        return usersRepository.save(userEntity);
	}
	
	public String login (LoginRequest loginRequest) {
		if (StringUtils.isEmpty(loginRequest.getUserName())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        if (StringUtils.isEmpty(loginRequest.getPassword())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        UserEntity userEntity = usersRepository.findByUserNameEquals(loginRequest.getUserName());
        if (userEntity == null) return MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID;
        if (!loginRequest.getPassword().equals(userEntity.getPassword())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID;
        return null;
	}
}
