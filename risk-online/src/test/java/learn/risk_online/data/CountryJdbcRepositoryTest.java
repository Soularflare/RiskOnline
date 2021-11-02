package learn.risk_online.data;

import learn.risk_online.models.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CountryJdbcRepositoryTest {

    @Autowired
    CountryJdbcRepository repository;

    @Autowired
    SetKnowGoodState knowGoodState;

    @BeforeEach
    void setup(){
        knowGoodState.set();
    }


    //Retrieval Tests

    @Test
    void shouldFindAll(){
        List<Country> countries = repository.findAll(1);
        assertNotNull(countries);
        assertTrue(countries.size() == 42);
    }

    @Test
    void shouldFindByCountryId(){
        Country alaska = repository.findById(1, 0);
        assertNotNull(alaska);
        assertEquals(alaska.getCountryName(), ("ALASKA"));

        Country nAfrica = repository.findById(2, 13);
        assertNotNull(nAfrica);
        assertEquals(nAfrica.getCountryName(), "N_AFRICA");
    }



    //Update Tests
    @Test
    void shouldUpdateAlaskaGameState(){
        Country alaska = repository.findById(1, 0);
        assertEquals(3, alaska.getArmy());
        assertEquals(1, alaska.getPlayerPossession());

        alaska.setGameId(1);
        alaska.setCountryId(0);
        alaska.setArmy(6);
        alaska.setPlayerPossession(3);

        assertTrue(repository.update(alaska));
        Country alaskaUpdated = repository.findById(1, 0);
        assertEquals(6, alaskaUpdated.getArmy());
        assertEquals(3, alaskaUpdated.getPlayerPossession());

    }
}