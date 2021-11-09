package learn.risk_online.data;

import learn.risk_online.data.mappers.ProfileMapper;
import learn.risk_online.data.mappers.ProfileMicrotransactionMapper;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Game;
import learn.risk_online.models.Profile;
import learn.risk_online.models.ProfileMicrotransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository
public class ProfileJdbcRepository implements ProfileRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ProfileMicrotransactionRepository pmRepository;
    private final GameRepository gameRepository;


    public ProfileJdbcRepository(JdbcTemplate jdbcTemplate,
                                 ProfileMicrotransactionRepository pmRepository, GameRepository gameRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pmRepository = pmRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Profile findByProfileId(String profileId) {
        final String sql = "select up.profile_id, gu.user_id, up.total_games, " +
                "up.wins, up.game_time, up.points, gu.user_name " +
                "from user_profile up " +
                "inner join game_user gu on gu.user_id = up.user_id " +
                "where up.profile_id = ?;";

        Profile profile = jdbcTemplate.query(sql, new ProfileMapper(), profileId)
                .stream().findFirst().orElse(null);

        if(profile != null){
            addMicrotransactions(profile);
        }
        return profile;
    }

    @Override
    public Profile findByUserId(String userId) {
        final String sql = "select up.profile_id, gu.user_id, up.total_games, " +
                "up.wins, up.game_time, up.points, gu.user_name " +
                "from user_profile up " +
                "inner join game_user gu on gu.user_id = up.user_id " +
                "where up.user_id = ?;";

        Profile profile = jdbcTemplate.query(sql, new ProfileMapper(), userId)
                .stream().findFirst().orElse(null);

        if(profile != null){
            addMicrotransactions(profile);
            addGames(profile);
        }

        return profile;
    }


    @Override
    public Profile add(AppUser user) {
        final String sql = "insert into user_profile (user_id) " +
                "values (?);";

        boolean success = jdbcTemplate.update(sql, user.getUserId()) > 0;

        if(success){
            return findByUserId(user.getUserId());
        }
        return null;
    }

    @Override
    public boolean update(Profile profile) {
        final String sql = "update user_profile set " +
                "total_games = ?, " +
                "wins = ?, " +
                "game_time = ?, " +
                "points = ? " +
                "where profile_id = ?;";
        return (jdbcTemplate.update(sql, profile.getTotalGames(), profile.getWins(),
                profile.getGameTime(), profile.getPoints(), profile.getProfileId()) > 0);
    }

    @Override
    @Transactional
    public boolean deleteByProfileId(String profileId) {
        Profile profile = findByProfileId(profileId);

        if(profile != null){
            List<ProfileMicrotransaction> microtransactions = profile.getMicrotransactions();
            for(ProfileMicrotransaction pm : microtransactions){
                pmRepository.deleteByKeys(pm.getMicrotransaction().getId(), profileId);
            }
        }
        return (jdbcTemplate.update("delete from user_profile where profile_id = ?;", profileId) > 0);
    }




    private void addMicrotransactions(Profile profile){
        final String sql = "select up.profile_id, upm.equiped, upm.micro_id, m.product, m.price " +
                "from user_profile up " +
                "inner join user_profile_microtransaction upm on upm.profile_id = up.profile_id " +
                "inner join microtransaction m on m.micro_id = upm.micro_id " +
                "where up.profile_id = ?;";

        List<ProfileMicrotransaction> microtransactions = jdbcTemplate.query(sql,
                new ProfileMicrotransactionMapper(), profile.getProfileId());
        profile.setMicrotransactions(microtransactions);
    }

    private void addGames(Profile profile){
        List<Game> games = gameRepository.findAll(profile.getUserId());
        profile.setOngoingGames(games);
    }
}
