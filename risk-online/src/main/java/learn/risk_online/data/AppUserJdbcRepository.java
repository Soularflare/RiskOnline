package learn.risk_online.data;

import learn.risk_online.data.mappers.AppUserMapper;
import learn.risk_online.data.mappers.PlayerMapper;
import learn.risk_online.data.mappers.ProfileMapper;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Player;
import learn.risk_online.models.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppUserJdbcRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final GameJdbcRepository gamerepo;
    private final ProfileJdbcRepository profilerepo;

    public AppUserJdbcRepository(JdbcTemplate jdbcTemplate, GameJdbcRepository gamerepo, ProfileJdbcRepository profilerepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.gamerepo = gamerepo;
        this.profilerepo = profilerepo;
    }

    @Override
    public List<AppUser> findAll() {
        final String sql = "select * from game_user;";
        return jdbcTemplate.query(sql, new AppUserMapper());
    }

    @Override
    public AppUser findById(String id) {
        final String sql = "select * from game_user where user_id = ?;";
        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.
            return jdbcTemplate.queryForObject(sql, new AppUserMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }

    @Override
    public boolean add(AppUser user) {
        final String sql = " insert into game_user (user_name,password_hash,disabled) values (?,?,?);";

        return (jdbcTemplate.update(sql,  user.getUserName(),user.getPassword(),
                user.isDisabled()) > 0);

    }

    @Override
    public boolean update(AppUser user) {
        final String sql = "update game_user set user_name = ?, password_hash = ?, disabled = ? where user_id =?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                user.getUserName(),user.getPassword(),user.isDisabled(),user.getUserId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(String id) {
        final String sql1 = "select gp.game_id, gp.turn_order, gp.user_id, gu.user_name " +
                "from game_player gp " +
                "left outer join game_user gu on gu.user_id = gp.user_id " +
                "where gp.user_id = ?;";
        List<Player> stuff= jdbcTemplate.query(sql1, new PlayerMapper(),id);
        for(Player p :stuff)
        {
            gamerepo.deleteById(p.getGameId());
        }

        final String sql2 = "select up.profile_id, gu.user_id, up.total_games, " +
                "up.wins, up.game_time, up.points, gu.user_name " +
                "from user_profile up " +
                "inner join game_user gu on gu.user_id = up.user_id " +
                "where up.user_id = ?;";
        List<Profile> stuff2= jdbcTemplate.query(sql2, new ProfileMapper(),id);
        for(Profile p :stuff2)
        {
            profilerepo.deleteByProfileId(p.getProfileId());
        }
        final String sql3 = "delete from game_user_role where user_id = ?;";
        jdbcTemplate.update(sql3, id);
        final String sql = "delete from game_user where user_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
