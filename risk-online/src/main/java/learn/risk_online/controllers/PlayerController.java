//package learn.risk_online.controllers;
//
//
//import learn.risk_online.domain.PlayerService;
//import learn.risk_online.domain.Result;
//import learn.risk_online.models.Player;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
//@RequestMapping("/api/players")
//public class PlayerController {
//
//    private final PlayerService playerService;
//
//    public PlayerController(PlayerService playerService) {
//        this.playerService = playerService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody List<Player> playerList){
//        Result<Player> result = playerService.addPlayers(playerList);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
//    }
//}
