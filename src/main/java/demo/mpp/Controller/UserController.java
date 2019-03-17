package demo.mpp.Controller;

import demo.mpp.Constant.MessageConstant;
import demo.mpp.Constant.URL;
import demo.mpp.Entity.UserEntity;
import demo.mpp.Request.LoginRequest;
import demo.mpp.Request.UserRequest;
import demo.mpp.Response.LoginResponse;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
	private UserService userService;
	
	@RequestMapping(value = URL.USER_BASE_URL,method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createUser(@RequestBody UserRequest userRequest) {
		try {
			String exceptionMessage = userService.validateCreateUser(userRequest.getUser());
            if (exceptionMessage != null) {
                return new ResponseEntity<>(false, exceptionMessage);
            } else {
				UserEntity userEntity = userService.createUser(userRequest.getUser());
				if (userEntity.getId()!=null) {
					return new ResponseEntity<>(true,null, MessageConstant.CREATE_SUCCESS.replaceAll("@@user@@", userEntity.getUserName()));
				} else {
					return new ResponseEntity<>(false,MessageConstant.CREATE_USER_FAIL.replaceAll("@@user@@", userEntity.getUserName()));
				}
            }
		} catch (Exception e) {
			return new ResponseEntity<> (false,e.getMessage());
		}
	}
	

	@RequestMapping(value ="login",method = RequestMethod.POST)
    public @ResponseBody
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
		try {
			return userService.login(loginRequest);
		} catch (Exception e) {
			return new LoginResponse(e.getMessage());
		}
	}

	@RequestMapping(value ="/profile/{user_name}",method = RequestMethod.GET)
	public @ResponseBody UserEntity getProfile(@PathVariable("user_name") String useName) {
		return userService.getUserProfile(useName);
	}
}
