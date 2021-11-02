package learn.risk_online.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CountryJdbcRepositoryTest {

    @Autowired
    CountryJdbcRepository repository;


    @Test
    void shouldFindAll(){

    }

}