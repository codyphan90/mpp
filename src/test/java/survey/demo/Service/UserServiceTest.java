package survey.demo.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Entity.UserEntity;
import survey.demo.Repository.SurveyRepository;
import survey.demo.Repository.UserRepository;
import survey.demo.Request.LoginRequest;
import survey.demo.Response.LoginResponse;
import survey.demo.utils.Common;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserEntity userEntity;
    LoginRequest loginRequest;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Before
    public void setUp() {
        buildUserEntity();
    }

    public void buildUserEntity() {
        userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUserName("Nam");
        userEntity.setPassword("1234");
        userEntity.setIsAdmin(true);

        loginRequest = new LoginRequest();
        loginRequest.setUser(userEntity);
    }

    @Test
    public void validateCreateUser_whenSuccess() {
        when(userRepositoryMock.countByUserName(any())).thenReturn(0);
        String result = userService.validateCreateUser(userEntity);
        assertNull(result);
    }

    @Test
    public void validateCreateUser_whenFailed() {
        when(userRepositoryMock.countByUserName(any())).thenReturn(1);
        String result = userService.validateCreateUser(userEntity);
        assertNotNull(result);
    }

    @Test
    public void createUser_whenSuccess() {
        when(userRepositoryMock.save(any())).thenReturn(userEntity);

        UserEntity result = userService.createUser(userEntity);
        assertEquals(userEntity.getId(),result.getId());
    }

    @Test
    public void createUser_whenFailed() {
        String oldUserName=userEntity.getUserName();
        userEntity.setUserName("");

        UserEntity result = userService.createUser(userEntity);
        userEntity.setUserName(oldUserName);
        assertNull(result);
    }

    @Test
    public void login_whenSuccess() {
        UserEntity tempEntity = new UserEntity();
        tempEntity.setUserName(userEntity.getUserName());
        tempEntity.setPassword(Common.hash(userEntity.getPassword()));
        tempEntity.setIsAdmin(userEntity.getIsAdmin());
        when(userRepositoryMock.findByUserNameEquals(any())).thenReturn(tempEntity);

        LoginResponse result = userService.login(loginRequest);
        assertEquals(result.getSuccess(),true);
    }

    @Test
    public void login_whenFailed() {
        when(userRepositoryMock.findByUserNameEquals(any())).thenReturn(null);

        LoginResponse result = userService.login(loginRequest);
        assertEquals(result.getSuccess(),false);
    }
}