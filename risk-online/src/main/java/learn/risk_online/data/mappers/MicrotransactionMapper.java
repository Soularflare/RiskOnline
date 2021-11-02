package learn.risk_online.data.mappers;

import learn.risk_online.models.Microtransaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MicrotransactionMapper implements RowMapper<Microtransaction> {
    @Override
    public Microtransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Microtransaction microt = new Microtransaction();
        microt.setId(rs.getInt("micro_id"));
        microt.setProduct(rs.getString("product"));
        microt.setPrice(rs.getInt("price"));
        return microt;
    }
}
