package learn.risk_online.data.mappers;

import learn.risk_online.models.Microtransaction;
import learn.risk_online.models.MicrotransactionProfile;
import learn.risk_online.models.ProfileMicrotransaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMicrotransactionMapper implements RowMapper<ProfileMicrotransaction> {

    @Override
    public ProfileMicrotransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfileMicrotransaction profileMicrotransaction = new ProfileMicrotransaction();
        profileMicrotransaction.setProfileId(rs.getString("profile_id"));
        profileMicrotransaction.setEquipped(rs.getBoolean("equiped"));

        MicrotransactionMapper microtransactionMapper = new MicrotransactionMapper();
        profileMicrotransaction.setMicrotransaction(microtransactionMapper.mapRow(rs, rowNum));

        return profileMicrotransaction;
    }
}
