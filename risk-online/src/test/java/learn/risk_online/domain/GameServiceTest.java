package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import learn.risk_online.data.GameJdbcRepository;
import learn.risk_online.data.PlayerRepository;
import learn.risk_online.models.Country;
import learn.risk_online.models.Game;
import learn.risk_online.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    GameService service;

    @MockBean
    GameJdbcRepository gameRepository; //why need jdbc when interface should work???

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    PlayerRepository playerRepository;

    List<Country> countries = List.of(new Country(1, 0, "C", 0, 2),
            new Country(1,1, "C", 0, 2), new Country(1,2, "C", 0, 2),
            new Country(1,3, "C", 0, 2), new Country(1,4, "C", 0, 2),
            new Country(1,5, "C", 0, 2), new Country(1,6, "C", 0, 2),
            new Country(1,7, "C", 0, 2), new Country(1,8, "C", 0, 2),
            new Country(1,9, "C", 0, 2), new Country(1,10, "C", 0, 2),
            new Country(1,11, "C", 0, 2), new Country(1,12, "C", 0, 2),
            new Country(1,13, "C", 0, 2), new Country(1,14, "C", 0, 2),
            new Country(1,15, "C", 0, 2), new Country(1,16, "C", 0, 2),
            new Country(1,17, "C", 0, 2), new Country(1,18, "C", 0, 2),
            new Country(1,19, "C", 0, 2), new Country(1,20, "C", 0, 2),
            new Country(1,21, "C", 0, 2), new Country(1,22, "C", 0, 2),
            new Country(1,23, "C", 0, 2), new Country(1,24, "C", 0, 2),
            new Country(1,25, "C", 0, 2), new Country(1,26, "C", 0, 2),
            new Country(1,27, "C", 0, 2), new Country(1,28, "C", 0, 2),
            new Country(1,29, "C", 0, 2), new Country(1,30, "C", 0, 2),
            new Country(1,31, "C", 0, 2), new Country(1,32, "C", 0, 2),
            new Country(1,33, "C", 0, 2), new Country(1,34, "C", 0, 2),
            new Country(1,35, "C", 0, 2), new Country(1,36, "C", 0, 2),
            new Country(1,37, "C", 0, 2), new Country(1,38, "C", 0, 2),
            new Country(1,39, "C", 0, 2), new Country(1,40, "C", 0, 2),
            new Country(1,41, "C", 1, 2));
//Add Game Tests
    @Test
    void shouldAdd(){
        Player player1 = new Player();
        player1.setGameId(4);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);
        Player player2 = new Player();
        player2.setGameId(4);
        player2.setUserId(null);
        player2.setTurnOrder(2);
        List<Player> playerList = List.of(player1, player2);

        List<Country> c = countries;
        for(Country country: c){
            country.setGameId(4);
        }

        Game game = new Game();//
        game.setPlayerTurn(0);
        game.setTimeElapsed(0);
        game.setCountryList(countries);
        game.setPlayers(playerList);

        Game mockOut = new Game();
        mockOut.setGameId(4);
        mockOut.setTimeElapsed(0);
        mockOut.setPlayerTurn(0);
        mockOut.setCountryList(c);
        mockOut.setPlayers(playerList);

        when(gameRepository.add(game)).thenReturn(mockOut);
        when(countryRepository.add(any())).thenReturn(true);
        when(playerRepository.add(any())).thenReturn(true);

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

        List<Country> c = countries;
        for(Country country: c){
            country.setGameId(4);
        }
        game.setCountryList(c);


        when(gameRepository.update(game)).thenReturn(true);
        when(countryRepository.update(any())).thenReturn(true);

        Result<Game> expected = new Result<>();
        Result<Game> actual = service.updateGame(game);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

}