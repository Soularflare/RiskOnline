package learn.risk_online.data;

import learn.risk_online.models.Country;
import learn.risk_online.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerJdbcRepositoryTest {

    @Autowired
    PlayerJdbcRepository repository;

    @Autowired
    KnownGoodState knowGoodState;

    @BeforeEach
    void setup(){ knowGoodState.set(); }

    //Retrieval Tests

    @Test
    void shouldFindAll(){
        List<Player> players = repository.findAll(1);
        assertNotNull(players);
        assertTrue(players.size() == 3);
    }

    @Test
    void shouldFindByCountryId(){
        Player one = repository.findByOrder(1, 1);
        assertNotNull(one);
        assertEquals(one.getUserName(), "user");
        assertEquals(one.getUserId(),"4d980627-3b3c-11ec-8708-0242ac110002");

        Player two = repository.findByOrder(1, 2);
        assertNotNull(two);
        assertEquals(two.getUserName(), "CPU");
        assertEquals(two.getUserId(),null);
    }

    //addPlayers test
    @Test
    void shouldAddPlayersGamePlayer(){
        Player one = new Player();
        one.setGameId(4);
        one.setUserName("User");
        one.setTurnOrder(1);
        one.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");

        assertTrue(repository.add(one));

        Player two = new Player();
        two.setGameId(4);
        two.setUserName("CPU");
        two.setTurnOrder(2);
        two.setUserId(null);

        assertTrue(repository.add(two));

        List<Player> players = repository.findAll(4);
        assertNotNull(players);
        assertEquals("4d980627-3b3c-11ec-8708-0242ac110002", players.get(0).getUserId());
        assertEquals(null, players.get(1).getUserId());

    }

    @Test
    void shouldDeleteAllGameID2(){
        assertTrue(repository.deleteAllPlayersById(2));

        List<Player> players = repository.findAll(2);
        assertEquals(0, players.size());
    }


}