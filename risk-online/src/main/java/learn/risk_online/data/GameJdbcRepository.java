package learn.risk_online.data;

import learn.risk_online.models.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameJdbcRepository implements GameRepository{
    @Override
    public List<Game> findAll(String userId) {
        return null;
    }

    @Override
    public Game findById(int gameId) {
        return null;
    }

    @Override
    public Game add(Game game) {
        return null;
    }

    @Override
    public boolean update(Game game) {
        return false;
    }

    @Override
    public boolean deleteById(int gameId) {
        return false;
    }


}
