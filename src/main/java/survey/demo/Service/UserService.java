package survey.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import survey.demo.Entity.UserEntity;
import survey.demo.Repository.UserRepository;
import survey.demo.Request.*;
import survey.demo.Constant.*;
import survey.demo.utils.Common;

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
	
	public String login (LoginRequest loginRequest) {
		logger.info("Validate userName [{}] login", loginRequest.getUserName());
		if (StringUtils.isEmpty(loginRequest.getUserName())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        if (StringUtils.isEmpty(loginRequest.getPassword())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_BLANK;
        UserEntity userEntity = usersRepository.findByUserNameEquals(loginRequest.getUserName());
        if (userEntity == null) return MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID;
        String loginPassword = Common.hash(loginRequest.getPassword());
        if (!loginPassword.equals(userEntity.getPassword())) return MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID;
        return null;
	}
}
