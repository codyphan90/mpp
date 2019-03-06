package survey.demo.Controller;
import survey.demo.Response.*;
import survey.demo.Service.UserService;
import survey.demo.Entity.*;
import survey.demo.Constant.*;
import survey.demo.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
	private UserService userService;
	
	@RequestMapping(value = URL.USER_BASE_URL,method = RequestMethod.POST)
    public @ResponseBody ResponseEntity createUser(@RequestBody UserRequest userRequest) {
		try {
			String exceptionMessage = userService.validateCreateUser(userRequest.getUser());
            if (exceptionMessage != null) {
                return new ResponseEntity<>(false, exceptionMessage);
            } else {
				UserEntity userEntity = userService.createUser(userRequest.getUser());
				if (userEntity.getId()!=null) {
					return new ResponseEntity<>(true,null,MessageConstant.CREATE_SUCCESS.replaceAll("@@user@@", userEntity.getUserName()));
				} else {
					return new ResponseEntity<>(false,MessageConstant.CREATE_USER_FAIL.replaceAll("@@user@@", userEntity.getUserName()));
				}
            }
		} catch (Exception e) {
			return new ResponseEntity<> (false,e.getMessage());
		}
	}
	

	@RequestMapping(value ="login",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		try {
			String exceptionMessage = userService.login(loginRequest);
			if (exceptionMessage != null) {
				return new ResponseEntity<>(false, exceptionMessage);
			} else {
			       return new ResponseEntity<>(MessageConstant.CREATE_SUCCESS.replaceAll("@@user@@", loginRequest.getUserName()));
			}
		} catch (Exception e) {
			return new ResponseEntity<> (false,e.getMessage());
		}
	}	
}
