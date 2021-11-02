package learn.risk_online.data.mappers;

import learn.risk_online.models.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GameMapper implements RowMapper<Game> {


    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setGameId(rs.getInt("game_id"));
        game.setTimeElapsed(rs.getInt("time_elapsed"));
        game.setPlayerTurn(rs.getInt("player_turn"));

        return game;
    }
}
