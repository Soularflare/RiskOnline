package learn.risk_online.data;

import learn.risk_online.models.ProfileMicrotransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileMicrotransactionJdbcRepository implements ProfileMicrotransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileMicrotransactionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ProfileMicrotransaction profileMicrotransaction) {
        final String sql = "insert into user_profile_microtransaction " +
                "(profile_id, micro_id, equiped) " +
                "values (?, ?, ?);";
        return jdbcTemplate.update(sql, profileMicrotransaction.getProfileId(),
                profileMicrotransaction.getMicrotransaction().getId(),
                profileMicrotransaction.isEquipped()) > 0;
    }

    @Override
    public boolean update(ProfileMicrotransaction profileMicrotransaction) {
        final String sql = "update user_profile_microtransaction set " +
                "equiped = ? " +
                "where profile_id = ? and micro_id = ?;";

        return jdbcTemplate.update(sql,
                profileMicrotransaction.isEquipped(),
                profileMicrotransaction.getProfileId(),
                profileMicrotransaction.getMicrotransaction().getId()) > 0;
    }

    @Override
    public boolean deleteByKeys(int microtransactionId, String profileId) {

        final String sql = "delete from user_profile_microtransaction " +
                "where profile_id = ? and micro_id = ?;";

        return jdbcTemplate.update(sql, profileId, microtransactionId) > 0;
    }
}