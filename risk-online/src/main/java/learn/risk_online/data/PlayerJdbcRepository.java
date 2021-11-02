package learn.risk_online.data;

import learn.risk_online.data.mappers.PlayerMapper;
import learn.risk_online.models.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerJdbcRepository implements PlayerRepository{

    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Player> findAll(int gameId) {
        final String sql =  "select gp.game_id, gp.turn_order, gp.user_id, gu.user_name " +
                "from game_player gp " +
                "left outer join game_user gu on gu.user_id = gp.user_id " +
                "where game_id = ?;";

            return jdbcTemplate.query(sql, new PlayerMapper(), gameId);
    }

    @Override
    public Player findByOrder(int gameId, int turnOrder) {
        return findAll(gameId).stream()
                .filter(p -> p.getTurnOrder() == turnOrder).findFirst().orElse(null);
    }

    @Override
    public boolean add(Player player) {
        final String sql = "insert into game_player (game_id, turn_order, user_id) " +
                "values (?, ?, ?);";

        return (jdbcTemplate.update(sql, player.getGameId(), player.getTurnOrder(),
                player.getUserId()) > 0);
    }

    @Override
    public boolean deleteAllPlayersById(int gameId) {
        final String sql = "delete from game_player " +
                "where game_id = ?;";

        return (jdbcTemplate.update(sql, gameId) > 0);
    }
}
