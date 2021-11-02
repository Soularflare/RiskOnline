package learn.risk_online.data.mappers;

import learn.risk_online.models.Player;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements RowMapper<Player> {
    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setGameId(rs.getInt("game_id"));
        player.setTurnOrder(rs.getInt("turn_order"));
        player.setUserId(rs.getString("user_id"));
        player.setUserName(rs.getString("user_name"));

        if (player.getUserId() == null){
            player.setUserName("CPU");
        }

        return player;
    }
}
