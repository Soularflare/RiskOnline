package learn.risk_online.domain;

import learn.risk_online.data.AppUserRepository;
import learn.risk_online.data.ProfileRepository;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @MockBean
    AppUserRepository appUserRepository;

    @MockBean
    ProfileRepository profileRepository;

    @Test
    void shouldAddUser(){
        AppUser user = new AppUser("userOne", "password1!", false);
        AppUser ex = new AppUser("4d980a71-3b3c-11ec-8708-0242ac112222", "userOne", "password1!", false);
        Profile profile = new Profile("abc123", "name", 0, 0, 0, 0);

        when(appUserRepository.add(any())).thenReturn(ex);
        when(profileRepository.add(any())).thenReturn(profile);

        Result<AppUser> expected = new Result<>();
        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddNull(){
        AppUser user = null;

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user cannot be null");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddMissingUsername(){
        AppUser user = new AppUser(null, "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("username is required");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddUserNameInUse(){
        AppUser user = new AppUser("name", "password123!", false);
        AppUser user1 = new AppUser("123abc", "name", "password123!", false);
        when(appUserRepository.findByUserName(any())).thenReturn(user1);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("username is already in use");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddInvalidPassword(){
        AppUser user = new AppUser("name", "password", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("password must contain a digit, letter, and special character");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddInvalidPassword2(){
        AppUser user = new AppUser("name", "pas", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("password must be at least 8 characters");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithSetID(){
        AppUser user = new AppUser("123abc", "name", "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user id cannot be set for new user");

        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }


    //update

    @Test
    void shouldUpdateUser(){
        AppUser user = new AppUser("4d980a71-3b3c-11ec-8708-0242ac112222", "userOne", "password1!", false);

        when(appUserRepository.update(any())).thenReturn(true);

        Result<AppUser> expected = new Result<>();
        Result<AppUser> actual = service.update(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateNull(){
        AppUser user = null;

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user cannot be null");

        Result<AppUser> actual = service.update(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateMissingUsername(){
        AppUser user = new AppUser(null, "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("username is required");

        Result<AppUser> actual = service.update(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateMissingId(){
        AppUser user = new AppUser("name", "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user id must be set for update");

        Result<AppUser> actual = service.update(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    //change password


    @Test
    void shouldChangePasswordUpdate(){
        AppUser user = new AppUser("4d980a71-3b3c-11ec-8708-0242ac112222", "userOne", "password1!", false);

        when(appUserRepository.update(any())).thenReturn(true);

        Result<AppUser> expected = new Result<>();
        Result<AppUser> actual = service.changePassword(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotChangePasswordUpdateNull(){
        AppUser user = null;

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user cannot be null");

        Result<AppUser> actual = service.changePassword(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotChangePasswordUpdateMissingUsername(){
        AppUser user = new AppUser(null, "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user id must be set to change password");

        Result<AppUser> actual = service.changePassword(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotChangePasswordUpdateMissingId(){
        AppUser user = new AppUser("name", "password123!", false);

        Result<AppUser> expected = new Result<>();
        expected.addErrorMessage("user id must be set for update");

        Result<AppUser> actual = service.update(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }







}