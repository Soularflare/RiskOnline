package learn.risk_online.domain;

import learn.risk_online.data.GameJdbcRepository;
import learn.risk_online.models.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    GameService service;

    @MockBean
    GameJdbcRepository gameRepository; //why need jdbc when interface should work???


//Add Game Tests
    @Test
    void shouldAdd(){
        Game game = new Game();//
        game.setPlayerTurn(0);
        game.setTimeElapsed(0);

        Game mockOut = new Game();
        mockOut.setGameId(4);
        mockOut.setTimeElapsed(0);
        mockOut.setPlayerTurn(0);

        when(gameRepository.add(game)).thenReturn(mockOut);

        Result<Game> actual = service.addGame(game);
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWithPosID(){
        Game game = new Game();
        game.setGameId(4);
        game.setPlayerTurn(0);
        game.setTimeElapsed(0);

        Result<Game> expected = new Result<>();
        expected.addErrorMessage("gameId cannot be set for adding new game");

        Result<Game> actual = service.addGame(game);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddNullGame(){
        Game game = null;

        Result<Game> expected = new Result<>();
        expected.addErrorMessage("Game cannot be null");

        Result<Game> actual = service.addGame(game);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddInvalidPlayerTurn(){
        Game game = new Game();
        game.setPlayerTurn(9);
        game.setTimeElapsed(0);

        Result<Game> expected = new Result<>();
        expected.addErrorMessage("Player turn must be between 0 and 5");

        Result<Game> actual = service.addGame(game);
        assertEquals(expected.getMessages(), actual.getMessages());

        game.setPlayerTurn(-3);
        actual = service.addGame(game);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddInvalidTime(){
        Game game = new Game();
        game.setPlayerTurn(2);
        game.setTimeElapsed(-12);

        Result<Game> expected = new Result<>();
        expected.addErrorMessage("Time elapsed cannot be negative");

        Result<Game> actual = service.addGame(game);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldUpdate(){
        Game game = new Game();
        game.setGameId(4);
        game.setPlayerTurn(2);
        game.setTimeElapsed(0);


        when(gameRepository.update(game)).thenReturn(true);

        Result<Game> expected = new Result<>();
        Result<Game> actual = service.updateGame(game);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

}