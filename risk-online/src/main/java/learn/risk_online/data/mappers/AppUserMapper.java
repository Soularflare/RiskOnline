package learn.risk_online.data.mappers;


import learn.risk_online.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AppUserMapper implements RowMapper<AppUser> {


    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser user = new AppUser();
        user.setUserId(rs.getString("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password_hash"));
        user.setDisabled(rs.getBoolean("disabled"));
        return user;
    }
}
