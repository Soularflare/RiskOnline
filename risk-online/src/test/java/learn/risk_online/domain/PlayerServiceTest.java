package learn.risk_online.domain;


import learn.risk_online.data.PlayerRepository;
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
class PlayerServiceTest {

    @Autowired
    PlayerService service;

    @MockBean
    PlayerRepository playerRepository;



    @Test
    void shouldAddPlayers(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);

        Player player2 = new Player();
        player2.setGameId(1);
        player2.setUserId(null);
        player2.setTurnOrder(2);

        List<Player> playerList = List.of(player1, player2);
        when(playerRepository.add(any())).thenReturn(true);

        Result<Player> expected = new Result<>();
        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddNullOrEmptyList(){
        List<Player> playerList = null;

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Players are required for a game");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());

        List<Player> players = new ArrayList<>();
        actual = service.addPlayers(players);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddOnlyOnePlayer(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);


        List<Player> playerList = List.of(player1);

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Minimum of 2 players must be saved");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

//    @Test
//    void shouldNotAddNullPlayers(){
//        List<Player> players = List.of(null, null);
//
//        Result<Player> expected = new Result<>();
//        expected.addErrorMessage("Players cannot be null");
//
//        Result<Player> actual = service.addPlayers(players);
//        assertEquals(expected.getMessages(), actual.getMessages());
//
//    }

    @Test
    void shouldNotAddWithoutGameId(){
        Player player1 = new Player();
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);

        Player player2 = new Player();
        player2.setUserId(null);
        player2.setTurnOrder(2);

        List<Player> playerList = List.of(player1, player2);

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Game id is required");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithSameTurnOrder(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);

        Player player2 = new Player();
        player2.setGameId(1);
        player2.setUserId(null);
        player2.setTurnOrder(1);

        List<Player> playerList = List.of(player1, player2);

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Players cannot have same turn order");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithDifferentGameId(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(1);

        Player player2 = new Player();
        player2.setGameId(2);
        player2.setUserId(null);
        player2.setTurnOrder(2);

        List<Player> playerList = List.of(player1, player2);

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Players must have same game ID");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithInvalidOrder(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        player1.setTurnOrder(9);

        Player player2 = new Player();
        player2.setGameId(1);
        player2.setUserId(null);
        player2.setTurnOrder(2);

        List<Player> playerList = List.of(player1, player2);

        Result<Player> expected = new Result<>();
        expected.addErrorMessage("Valid order number is required");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddWithoutOneUserId(){
        Player player1 = new Player();
        player1.setGameId(1);
        player1.setUserId(null);
        player1.setTurnOrder(1);

        Player player2 = new Player();
        player2.setGameId(1);
        player2.setUserId(null);
        player2.setTurnOrder(2);

        List<Player> playerList = List.of(player1, player2);


        Result<Player> expected = new Result<>();
        expected.addErrorMessage("At least one user id required to save game");

        Result<Player> actual = service.addPlayers(playerList);
        assertEquals(expected.getMessages(), actual.getMessages());
    }




}