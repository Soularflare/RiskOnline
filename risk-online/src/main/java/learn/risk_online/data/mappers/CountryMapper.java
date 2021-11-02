package learn.risk_online.data.mappers;

import learn.risk_online.models.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();

        country.setGameId(resultSet.getInt("game_id"));
        country.setCountryId(resultSet.getInt("country_id"));
        country.setCountryName(resultSet.getString("country"));
        country.setPlayerPossession(resultSet.getInt("player_possession"));
        country.setArmy(resultSet.getInt("army"));

        return country;
    }
}
