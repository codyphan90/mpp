package demo.mpp.Service;

import demo.mpp.Constant.MessageConstant;
import demo.mpp.Request.LoginRequest;
import demo.mpp.Response.LoginResponse;
import demo.mpp.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import demo.mpp.Entity.UserEntity;
import demo.mpp.Repository.UserRepository;
import survey.demo.Request.*;
import survey.demo.Constant.*;

@Service
public class UserService {
	private Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
    protected UserRepository usersRepository;
	@Autowired
    protected Common commonService;
	
	public String validateCreateUser(UserEntity userEntity) {
		logger.info("validate new userName [{}]", userEntity.getUserName());

        if (userEntity.getUserName() == null) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        if (userEntity.getPassword() == null) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        Integer entityCheckDuplicate = usersRepository.countByUserName(userEntity.getUserName());

        if (entityCheckDuplicate > 0) return String.format(MessageConstant.DUPLICATE_EXCEPTION_MESSAGE, "User " + userEntity.getUserName());
        return null;
	}
	
	public UserEntity createUser(UserEntity userEntity) {
		logger.info("Create new user with userName [{}]", userEntity.getUserName());
		if (!StringUtils.isEmpty(userEntity.getUserName())){
			userEntity.setPassword(Common.hash(userEntity.getPassword()));
			return usersRepository.save(userEntity);
		}
		return null;
	}
	
	public LoginResponse login (LoginRequest loginRequest) {
		logger.info("Validate userName [{}] login", loginRequest.getUser().getUserName());
		if (StringUtils.isEmpty(loginRequest.getUser().getUserName())) return new LoginResponse(MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK);
        if (StringUtils.isEmpty(loginRequest.getUser().getPassword())) return new LoginResponse(MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK);
        UserEntity userEntity = usersRepository.findByUserNameEquals(loginRequest.getUser().getUserName());
        if (userEntity == null) return new LoginResponse(MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID);
        String loginPassword = Common.hash(loginRequest.getUser().getPassword());
        if (!loginPassword.equals(userEntity.getPassword())) return new LoginResponse(MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID);
		logger.info("UserName [{}] login successfully", loginRequest.getUser().getUserName());
		return new LoginResponse(true,MessageConstant.LOGIN_SUCCESS.replaceAll("@@user@@", loginRequest.getUser().getUserName()),userEntity.getIsAdmin());
	}
}
