package learn.risk_online.data.mappers;

import learn.risk_online.models.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setProfileId(rs.getString("profile_id"));
        profile.setUserId(rs.getString("user_id"));
        profile.setTotalGames(rs.getInt("total_games"));
        profile.setWins(rs.getInt("wins"));
        profile.setGameTime(rs.getInt("game_time"));
        profile.setPoints(rs.getInt("points"));
        return profile;
    }
}
