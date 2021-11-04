package learn.risk_online.data;

import learn.risk_online.data.mappers.AppUserMapper;
import learn.risk_online.data.mappers.MicrotransactionMapper;
import learn.risk_online.models.AppUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class AppUserJdbcRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        final String sql = "delete from game_user where user_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
