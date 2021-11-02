package learn.risk_online.data;

import learn.risk_online.data.mappers.CountryMapper;
import learn.risk_online.models.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryJdbcRepository implements CountryRepository{

    private final JdbcTemplate jdbcTemplate;

    public CountryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> findAll(int gameId) {

        final String sql = "select c.country_id, c.country, gs.player_possession, gs.army " +
                "from countries c " +
                "inner join game_state gs on c.country_id = gs.country_id" +
                "where gs.game_id = ? and gs.country_id = ?;";

        return jdbcTemplate.query(sql, new CountryMapper());
    }

    @Override
    public Country findById(int gameId, int countryId) {

        return findAll(gameId).stream()
                .filter(c -> c.getCountryId() == countryId).findFirst().orElse(null);
    }

    @Override
    public Country add(Country country) {
        return null;
    }

    @Override
    public boolean update(Country country) {
        return false;
    }

    @Override
    public boolean deleteById(int countryId) {
        return false;
    }
}
