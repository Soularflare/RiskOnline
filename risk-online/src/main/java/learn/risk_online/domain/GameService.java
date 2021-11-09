package learn.risk_online.domain;

import learn.risk_online.data.GameRepository;
import learn.risk_online.models.Country;
import learn.risk_online.models.Game;
import learn.risk_online.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final CountryService countryService;
    private final PlayerService playerService;

    public GameService(GameRepository gameRepository, CountryService countryService, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.countryService = countryService;
        this.playerService = playerService;
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

    @Transactional
    public Result<Game> addGame(Game game){
        Result<Game> result = validateGame(game);
        if(!result.isSuccess()){
            return result;
        }

        if(game.getGameId() != 0){
            result.addErrorMessage("gameId cannot be set for adding new game");
            return result;
        }

        game = gameRepository.add(game);

        if(game == null){
            result.addErrorMessage("failed to save game");
            return result;
        }

        List<Country> countries = game.getCountryList();
        if(countries == null){
            result.addErrorMessage("Countries are required to save game");
            return result;
        }
        if(countries.size() == 0){
            result.addErrorMessage("Countries are required to save game");
            return result;
        }
        for(Country c : countries){
            c.setGameId(game.getGameId());
        }
        Result<Country>  countryResult = countryService.addCountries(countries);
        if(!countryResult.isSuccess()){
           for(String s : countryResult.getMessages()){
               result.addErrorMessage(s);
           }
           return result;
        }


        List<Player> players = game.getPlayers();
        if(players == null ){
            result.addErrorMessage("Players are required for a game");
            return result;
        }
        if(players.size() == 0 ){
            result.addErrorMessage("Players are required for a game");
            return result;
        }
        for(Player p : players){
            p.setGameId(game.getGameId());
        }
        Result<Player> playerResult = playerService.addPlayers(players);
        if(!playerResult.isSuccess()){
            for (String s : playerResult.getMessages()){
                result.addErrorMessage(s);
            }
            return result;
        }

        result.setPayload(game);
        return result;
    }

    public Result<Game> updateGame(Game game){
        Result<Game> result = validateGame(game);
        if(!result.isSuccess()){
            return result;
        }
        if(game.getGameId() <= 0){
            result.addErrorMessage("gameId required to update game");
            return result;
        }
        if(!gameRepository.update(game)){
            result.addErrorMessage(String.format("gameId %s failed to update", game.getGameId()));
            return result;
        }
        return result;
    }

    public boolean deleteGameById(int gameId){
        return gameRepository.deleteById(gameId);
    }

    private Result<Game> validateGame(Game game) {
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
