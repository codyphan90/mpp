package survey.demo.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Constant.MessageConstant;
import survey.demo.Entity.UserEntity;
import survey.demo.Request.LoginRequest;
import survey.demo.Request.UserRequest;
import survey.demo.Response.ResponseEntity;
import survey.demo.Service.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private UserRequest userRequest;
    private LoginRequest loginRequest;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userServiceMock;

    @Before
    public void setUp() {
        buildUserEntity();
    }

    public void buildUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUserName("Nam");
        userEntity.setPassword("1234");
        userEntity.setIsAdmin(true);

        userRequest = new UserRequest();
        userRequest.setUser(userEntity);

        loginRequest = new LoginRequest();
        loginRequest.setUser(userEntity);
    }

    @Test
    public void createUser_whenFailed() {
        String exception= String.format(MessageConstant.DUPLICATE_EXCEPTION_MESSAGE, "User " + userRequest.getUser().getUserName());
        when(userServiceMock.validateCreateUser(any())).thenReturn(exception);

        ResponseEntity responseEntity = userController.createUser(userRequest);
        assertEquals(exception, responseEntity.getExceptionMessage());
    }

    @Test
    public void createUser_whenSuccess() {
        when(userServiceMock.validateCreateUser(any())).thenReturn(null);
        when(userServiceMock.createUser(any())).thenReturn(userRequest.getUser());

        ResponseEntity responseEntity = userController.createUser(userRequest);
        assertEquals(true, responseEntity.getSuccess());
    }

    @Test
    public void login_whenFailed() {
        when(userServiceMock.login(any())).thenReturn(MessageConstant.USER_NAME_OR_PASSWORD_IS_INVALID);

        ResponseEntity responseEntity = userController.login(loginRequest);
        assertEquals(false, responseEntity.getSuccess());
    }

    @Test
    public void login_whenSuccess() {
        when(userServiceMock.login(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.login(loginRequest);
        assertEquals(true, responseEntity.getSuccess());
    }
}