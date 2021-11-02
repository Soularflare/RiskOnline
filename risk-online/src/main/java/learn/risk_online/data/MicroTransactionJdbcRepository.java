package learn.risk_online.data;

import learn.risk_online.data.mappers.MicrotransactionMapper;
import learn.risk_online.models.Microtransaction;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MicroTransactionJdbcRepository implements MicroTransactionRepository{

    private final JdbcTemplate jdbcTemplate;

    public MicroTransactionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Microtransaction> findAll() {
        final String sql = "select * from microtransaction;";
        return jdbcTemplate.query(sql, new MicrotransactionMapper());
    }

    @Override
    public Microtransaction findById(int id) {
        final String sql = "select * from microtransaction where micro_id = ?;";
        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.
            return jdbcTemplate.queryForObject(sql, new MicrotransactionMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }

    @Override
    public Microtransaction add(Microtransaction microt) {
        final String sql = "insert into microtransaction (product, price) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, microt.getProduct());
            ps.setInt(2, microt.getPrice());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        microt.setId(keyHolder.getKey().intValue());
        return microt;
    }

    @Override
    public boolean update(Microtransaction microt) {
        final String sql = "update microtransaction set "
                + "`product` = ?, "
                + "`price` = ? "
                + "where micro_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                microt.getProduct(),microt.getPrice(), microt.getId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "delete from microtransaction where micro_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
