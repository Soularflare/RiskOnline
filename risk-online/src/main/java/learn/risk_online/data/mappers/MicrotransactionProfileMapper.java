package learn.risk_online.data.mappers;

import learn.risk_online.models.MicrotransactionProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MicrotransactionProfileMapper implements RowMapper<MicrotransactionProfile> {

    @Override
    public MicrotransactionProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
        MicrotransactionProfile microtransactionProfile = new MicrotransactionProfile();
        microtransactionProfile.setMicrotransactionId(rs.getInt("micro_id"));
        microtransactionProfile.setEquipped(rs.getBoolean("equiped"));

        ProfileMapper profileMapper = new ProfileMapper();
        microtransactionProfile.setProfile(profileMapper.mapRow(rs, rowNum));

        return microtransactionProfile;
    }
}
