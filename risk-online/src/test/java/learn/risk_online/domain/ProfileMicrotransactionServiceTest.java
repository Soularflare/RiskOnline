package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import learn.risk_online.data.MicroTransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProfileMicrotransactionServiceTest {

    @Autowired
    ProfileMicrotransactionService service;

    @MockBean
    MicroTransactionRepository mtRepository;



}