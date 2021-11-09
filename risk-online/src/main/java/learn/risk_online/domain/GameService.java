package learn.risk_online.domain;

import learn.risk_online.data.GameRepository;
import learn.risk_online.models.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGames(String userId){
        return gameRepository.findAll(userId);
    }

    public Game findByGameId(int gameId){
        return gameRepository.findByGameId(gameId);
    }

    public Game findByUserAndGameId(String userId, int gameId){
        return gameRepository.findByUserAndGameId(userId, gameId);
    }

    public Result<Game> addGame(Game game){
        Result<Game> result = validate(game);
        if(!result.isSuccess()){
            return result;
        }

        if(game.getGameId() != 0){
            result.addErrorMessage("gameId cannot be set for adding new game");
            return result;
        }

        game = gameRepository.add(game);
        result.setPayload(game);
        return result;
    }

    public Result<Game> updateGame(Game game){
        Result<Game> result = validate(game);
        if(!result.isSuccess()){
            return result;
        }
        if(game.getGameId() <= 0){
            result.addErrorMessage("gameId required to update game");
            return result;
        }
        if(!gameRepository.update(game)){
            result.addErrorMessage(String.format("gameId %s failed to update", game.getGameId()));
        }
        return result;
    }

    public boolean deleteGameById(int gameId){
        return gameRepository.deleteById(gameId);
    }

    private Result<Game> validate(Game game) {
        Result<Game> result = new Result<>();
        if(game == null){
            result.addErrorMessage("Game cannot be null");
            return result;
        }
        if(game.getPlayerTurn() < 0 || game.getPlayerTurn() > 5){
            result.addErrorMessage("Player turn must be between 0 and 5");
        }
        if(game.getTimeElapsed() < 0){
            result.addErrorMessage("Time elapsed cannot be negative");
        }
        return result;
    }


}
