package learn.risk_online.controllers;

import learn.risk_online.domain.GameService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Game game){
        Result<Game> result = gameService.addGame(game);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Object> update(@PathVariable int gameId, @RequestBody Game game){
        if(gameId != game.getGameId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Game> result = gameService.updateGame(game);

        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }
}
