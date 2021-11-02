package learn.risk_online.data;

import learn.risk_online.data.mappers.CountryMapper;
import learn.risk_online.models.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CountryJdbcRepository implements CountryRepository{

    private final JdbcTemplate jdbcTemplate;

    public CountryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> findAll(int gameId) {

        final String sql = "select gs.game_id, c.country_id, c.country, gs.player_possession, gs.army " +
                "from countries c " +
                "inner join game_state gs on c.country_id = gs.country_id " +
                "where gs.game_id = ?;";

        return jdbcTemplate.query(sql, new CountryMapper(), gameId);
    }

    @Override
    public Country findById(int gameId, int countryId) {

        return findAll(gameId).stream()
                .filter(c -> c.getCountryId() == countryId).findFirst().orElse(null);
    }

    @Override
    public boolean add(Country country) {
        final String sql = "insert into game_state (game_id, country_id, player_possession, army) " +
                "values (?, ?, ?, ?);";

        return (jdbcTemplate.update(sql, country.getGameId(), country.getCountryId(),
            country.getPlayerPossession(), country.getArmy()) > 0);
    }

    @Override
    public boolean update(Country country) {
        final String sql = "update game_state set " +
                "player_possession = ?, " +
                "army = ? " +
                "where game_id = ? and country_id = ?;";

        return (jdbcTemplate.update(sql,country.getPlayerPossession(),
                country.getArmy(), country.getGameId(), country.getCountryId()) > 0);
    }

    @Override
    public boolean deleteById(int countryId) {
        return false;
    }
}
