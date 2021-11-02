package learn.risk_online.data;

import learn.risk_online.models.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CountryJdbcRepositoryTest {

    @Autowired
    CountryJdbcRepository repository;


    @Test
    void shouldFindAll(){
        List<Country> countries = repository.findAll(1);
        assertNotNull(countries);
        assertTrue(countries.size() == 42);
    }

}