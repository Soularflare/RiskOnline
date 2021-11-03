package learn.risk_online.data;

import learn.risk_online.data.mappers.GameMapper;
import learn.risk_online.models.Country;
import learn.risk_online.models.Game;
import learn.risk_online.models.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GameJdbcRepository implements GameRepository{

    private final JdbcTemplate jdbcTemplate;
    private final CountryRepository countryRepository;
    private final PlayerRepository playerRepository;

    public GameJdbcRepository(JdbcTemplate jdbcTemplate,
                              CountryRepository countryRepository,
                              PlayerRepository playerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.countryRepository = countryRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public List<Game> findAll(String userId) {

        final String sql = "select g.game_id, g.time_elapsed, g.player_turn " +
                "from game g " +
                "inner join game_player gp on gp.game_id = g.game_id " +
                "inner join game_user gu on gu.user_id = gp.user_id " +
                "where gu.user_id = ?;";

        List<Game> games = jdbcTemplate.query(sql, new GameMapper(), userId);

        if(games.size() > 0){
            for(Game g : games){
                addPlayersAndCountries(g);
            }
        }
        return games;
    }

    @Override
    @Transactional
    public Game findByGameId(int gameId) {
        final String sql = "select game_id, time_elapsed, player_turn " +
                "from game " +
                "where game_id = ?;";

        Game game = jdbcTemplate.query(sql, new GameMapper(), gameId)
                .stream().findFirst().orElse(null);

        if(game != null){ addPlayersAndCountries(game); }

        return game;
    }

    @Override
    public Game findByUserAndGameId(String userId, int gameId) {
        return findAll(userId).stream()
                .filter(game -> game.getGameId() == gameId)
                .findFirst().orElse(null);
    }

    @Override
    public Game add(Game game) {

        final String sql = "insert into game (time_elapsed, player_turn) " +
                "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, game.getTimeElapsed());
            ps.setInt(2, game.getPlayerTurn());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        game.setGameId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public boolean update(Game game) {

        final String sql = "update game set " +
                "time_elapsed = ?, " +
                "player_turn = ? " +
                "where game_id = ?;";

        return (jdbcTemplate.update(sql, game.getTimeElapsed(),
                game.getPlayerTurn(), game.getGameId()) > 0);
    } //maybe add update to countries when calling game update
    // -- need to make sure data not out of sync -- maybe in service

    @Override
    @Transactional
    public boolean deleteById(int gameId) {
        playerRepository.deleteAllPlayersById(gameId);
        countryRepository.deleteAllById(gameId);
        return (jdbcTemplate.update("delete from game where game_id = ?;", gameId) > 0);
    }


    private void addPlayersAndCountries(Game game){
        List<Player> players = playerRepository.findAll(game.getGameId());
        List<Country> countries = countryRepository.findAll(game.getGameId());
        game.setPlayers(players);
        game.setCountryList(countries);
    }


}
