package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import learn.risk_online.data.MicroTransactionRepository;
import learn.risk_online.data.ProfileMicrotransactionRepository;
import learn.risk_online.models.Microtransaction;
import learn.risk_online.models.Profile;
import learn.risk_online.models.ProfileMicrotransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProfileMicrotransactionServiceTest {

    @Autowired
    ProfileMicrotransactionService service;

    @MockBean
    ProfileMicrotransactionRepository pmtRepository;


    @Test
    void shouldAdd(){
        ProfileMicrotransaction profileMicro = new ProfileMicrotransaction("abc123", true);
        Microtransaction microtransaction = new Microtransaction(1, "abc", 100);
        profileMicro.setMicrotransaction(microtransaction);

        when(pmtRepository.add(any())).thenReturn(true);

        Result<ProfileMicrotransaction> expected = new Result<>();
        Result<ProfileMicrotransaction> actual = service.add(profileMicro);

        assertEquals(expected.getMessages(), actual.getMessages());
    }
}