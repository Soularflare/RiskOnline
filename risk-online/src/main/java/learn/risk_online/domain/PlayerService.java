package learn.risk_online.domain;

import learn.risk_online.data.PlayerRepository;
import learn.risk_online.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Result<Player> addPlayers(List<Player> players){
        Result<Player> result = new Result<>();

        if(players == null || players.isEmpty()){
            result.addErrorMessage("Players are required for a game");
            return result;
        }

        for(Player player : players){
            result = validate(player, players);
            if(!result.isSuccess()){
                return result;
            }
        }

        for(Player player : players){
            if(player.getUserId() == null){
                players.remove(player);
            }
        }
        if(players.size() == 0){
            result.addErrorMessage("At least one user id required to save game");
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

        if(player.getTurnOrder() < 0 || player.getTurnOrder() > 6){
            result.addErrorMessage("Valid order is required");
        }

        return result;
    }
}
