package learn.risk_online.data;


import learn.risk_online.models.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> findAll(int gameId);

    Player findByOrder(int gameId, int turnOrder);

    boolean add(Player player);

    boolean deleteAllPlayersById(int gameId);
}
