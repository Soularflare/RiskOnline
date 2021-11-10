package learn.risk_online.data;

import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;

public interface ProfileRepository {

    Profile findByProfileId(String profileId);

    Profile findByUserId(String userId);

    Profile add(String userId);

    boolean updateByProfileId(Profile profile);

    boolean updateByUserId(Profile profile);

    boolean deleteByProfileId(String profileId);

    boolean deleteByUserId(String userId);
}
