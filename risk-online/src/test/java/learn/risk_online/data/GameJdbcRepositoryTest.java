package learn.risk_online.data;

import learn.risk_online.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameJdbcRepositoryTest {

    @Autowired
    GameJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){ knownGoodState.set(); }


    //Retrieval Tests

    @Test
    void shouldFindAll(){
        List<Game> games = repository.findAll("4d980627-3b3c-11ec-8708-0242ac110002");
        assertNotNull(games);
        assertTrue(games.size() == 3);
    }

    @Test
    void shouldFindByGameId(){
        Game one = repository.findByGameId(1);
        assertNotNull(one);
        assertEquals(one.getTimeElapsed(), 111);
        assertEquals(one.getPlayerTurn(),1);
        assertEquals(one.getPlayers().size(), 3);
        assertEquals(one.getCountryList().size(), 42);
    }

    @Test
    void shouldFindByUserAndGameId(){
        Game one = repository.findByUserAndGameId("4d980627-3b3c-11ec-8708-0242ac110002", 1);
        assertNotNull(one);
        assertEquals(one.getTimeElapsed(), 111);
        assertEquals(one.getPlayerTurn(),1);
        assertEquals(one.getPlayers().size(), 3);
        assertEquals(one.getCountryList().size(), 42);
    }

    //add Game test
    @Test
    void shouldAddGame(){
        Game one = new Game();
        one.setTimeElapsed(0);
        one.setPlayerTurn(1);

        Game added = repository.add(one);
        one.setGameId(5);
        assertEquals(one, added);
    }

    //update game
    @Test
    void shouldUpdateGame(){
        Game four = repository.findByGameId(4);
        assertEquals(0, four.getTimeElapsed());
        assertEquals(1, four.getPlayerTurn());

        four.setTimeElapsed(20);
        four.setPlayerTurn(2);
        assertTrue(repository.update(four));

        Game updated = repository.findByGameId(4);
        assertEquals(20, updated.getTimeElapsed());
        assertEquals(2, updated.getPlayerTurn());
    }

    //delete game
    @Test
    void shouldDeleteGame2(){
        assertTrue(repository.deleteById(3));
        Game game = repository.findByGameId(3);
        assertNull(game);
    }
}