package learn.risk_online.domain;

import learn.risk_online.data.AppUserRepository;
import learn.risk_online.data.CountryRepository;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Player;
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

    @Test
    void shouldAddUser(){
        AppUser user = new AppUser("userOne", "password1!", false);
        AppUser ex = new AppUser("4d980a71-3b3c-11ec-8708-0242ac112222", "userOne", "password1!", false);


        when(appUserRepository.add(any())).thenReturn(ex);

        Result<AppUser> expected = new Result<>();
        Result<AppUser> actual = service.add(user);
        assertEquals(expected.getMessages(), actual.getMessages());
    }



}