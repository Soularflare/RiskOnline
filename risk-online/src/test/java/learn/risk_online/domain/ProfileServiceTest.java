package learn.risk_online.domain;

import learn.risk_online.data.MicroTransactionRepository;
import learn.risk_online.data.ProfileJdbcRepository;
import learn.risk_online.data.ProfileRepository;
import learn.risk_online.models.Game;
import learn.risk_online.models.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProfileServiceTest {

    @Autowired
    ProfileService service;

    @MockBean
    ProfileJdbcRepository profileRepository;

    @Test
    void shouldAddProfile(){
        Profile profile = new Profile("abc123", "name", 0, 0, 0, 0);

        when(profileRepository.add(any())).thenReturn(profile);

        Result<Profile> expected = new Result<>();
        Result<Profile> actual = service.addProfile("abc123");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithoutUserId(){

        Result<Profile> expected = new Result<>();
        expected.addErrorMessage("user id is required to create profile");

        Result<Profile> actual = service.addProfile(null);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldUpdateProfile(){
        Profile profile = new Profile("abc123", "name", 0, 0, 0, 0);

        when(profileRepository.updateByUserId(any())).thenReturn(true);

        Result<Profile> expected = new Result<>();
        Result<Profile> actual = service.updateProfile(profile);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateWithoutUserId(){
        Profile profile = new Profile(null, "name", 0, 0, 0, 0);

        Result<Profile> expected = new Result<>();
        expected.addErrorMessage("user id is required to update");

        Result<Profile> actual = service.updateProfile(profile);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateNull(){
        Result<Profile> expected = new Result<>();
        expected.addErrorMessage("profile is required to update");

        Result<Profile> actual = service.updateProfile(null);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotUpdateInvalid(){
        Profile profile = new Profile("abc123", "name", -12, 0, 0, 0);

        Result<Profile> expected = new Result<>();
        expected.addErrorMessage("Profile stats cannot be less than 0");

        Result<Profile> actual = service.updateProfile(profile);
        assertEquals(expected.getMessages(), actual.getMessages());
    }


}