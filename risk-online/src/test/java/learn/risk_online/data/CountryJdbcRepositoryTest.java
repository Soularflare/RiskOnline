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
    KnownGoodState knowGoodState;

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

        Country nAfrica = repository.findById(1, 13);
        assertNotNull(nAfrica);
        assertEquals(nAfrica.getCountryName(), "N_AFRICA");
    }


    //Add Test
    @Test
    void shouldAddCountryGameState(){
        Country alaska = new Country();
        alaska.setGameId(4);
        alaska.setCountryId(0);
        alaska.setPlayerPossession(1);
        alaska.setArmy(1);

        Country nwTerritory = new Country();
        nwTerritory.setGameId(4);
        nwTerritory.setCountryId(1);
        nwTerritory.setPlayerPossession(1);
        nwTerritory.setArmy(3);

        assertTrue(repository.add(alaska));
        assertTrue(repository.add(nwTerritory));

        Country alaskaAdded = repository.findById(4, 0);
        assertEquals(1, alaskaAdded.getArmy());
        assertEquals(1, alaskaAdded.getPlayerPossession());

        Country nwTerAdded = repository.findById(4, 1);
        assertEquals(3, nwTerAdded.getArmy());
        assertEquals(1, nwTerAdded.getPlayerPossession());
    }


    //Update Test
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

    @Test
    void shouldDeleteAllGameID2(){
        assertTrue(repository.deleteAllById(2));

        List<Country> countries = repository.findAll(2);
        assertEquals(0, countries.size());
    }
}