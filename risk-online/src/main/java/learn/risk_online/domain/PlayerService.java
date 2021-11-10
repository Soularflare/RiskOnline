package learn.risk_online.domain;

import learn.risk_online.data.PlayerRepository;
import learn.risk_online.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Result<Player> addPlayers(List<Player> players){
        Result<Player> result = new Result<>();
        if(players == null ){
            result.addErrorMessage("Players are required for a game");
            return result;
        }
        if(players.size() == 0 ){
            result.addErrorMessage("Players are required for a game");
            return result;
        }

        if(players.size() < 2){
            result.addErrorMessage("Minimum of 2 players must be saved");
            return result;
        }


        for(Player player : players){
            result = validate(player, players);
            if(!result.isSuccess()){
                return result;
            }
        }

        List<Player> playersWithUserId = new ArrayList<>();
        for(Player player : players){
            if(player.getUserId() != null){
                playersWithUserId.add(player);
            }
        }

        if(playersWithUserId.size() == 0){
            result.addErrorMessage("At least one user id required to save game");
            return result;
        }


        for(Player player : players){
            if(!playerRepository.add(player)){
                result.addErrorMessage("failed to add players");
                return result;
            }
        }
        return result;
    }

    private Result<Player> validate(Player player, List<Player> players) {
        Result<Player> result = new Result<>();

        if(player == null){
            result.addErrorMessage("Players cannot be null");
            return result;
        }

        if(player.getGameId() < 1){
            result.addErrorMessage("Game id is required");
        }

        if(players.stream()
               .filter(p -> p.getTurnOrder() == player.getTurnOrder())
               .collect(Collectors.toList()).size() > 1){
           result.addErrorMessage("Players cannot have same turn order");
        }

        if(!players.stream()
                .allMatch(p -> p.getGameId() == player.getGameId())){
            result.addErrorMessage("Players must have same game ID");
        }

        if(player.getTurnOrder() < 0 || player.getTurnOrder() >= 6){
            result.addErrorMessage("Valid order number is required");
        }

        return result;
    }
}
