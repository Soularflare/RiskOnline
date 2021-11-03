package learn.risk_online.data;

import learn.risk_online.models.Country;
import learn.risk_online.models.Game;

import java.util.List;

public interface GameRepository {

    List<Game> findAll(String userId); //or playerId?

    Game findByGameId(int gameId);

    Game findByUserAndGameId(String userId, int gameId);

    Game add(Game game);

    boolean update(Game game);

    boolean deleteById(int gameId);

}
