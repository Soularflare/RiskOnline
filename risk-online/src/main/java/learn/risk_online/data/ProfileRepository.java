package learn.risk_online.data;

import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;

public interface ProfileRepository {

    Profile findByProfileId(String profileId);

    Profile findByUserId(String userId);

    Profile add(AppUser user);

    boolean update(Profile profile);

    boolean deleteByProfileId(String profileId);
}
