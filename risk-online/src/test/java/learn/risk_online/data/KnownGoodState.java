package learn.risk_online.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class KnownGoodState {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasRun = false;

    void set() {
        if (!hasRun) {
            hasRun = true;
            jdbcTemplate.update("call set_known_good_state();");
            //jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
            //jdbcTemplate.update("call set_known_good_state_App_user();");
           // jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");

        }
    }
}

