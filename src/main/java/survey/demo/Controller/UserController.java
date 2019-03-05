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
    public @ResponseBody ResponseEntity createUser(@RequestBody UserEntity userEntity) {
		try {
			userEntity = userService.createUser(userEntity);
            return new ResponseEntity<>(MessageConstant.CREATE_SUCCESS.replaceAll("@@name@@", userEntity.getUserName()));
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
			       return new ResponseEntity<>(MessageConstant.CREATE_SUCCESS.replaceAll("@@name@@", loginRequest.getUserName()));
			}
		} catch (Exception e) {
			return new ResponseEntity<> (false,e.getMessage());
		}
	}	
}
