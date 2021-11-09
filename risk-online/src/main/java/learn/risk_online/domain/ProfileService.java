package learn.risk_online.domain;

import learn.risk_online.data.ProfileRepository;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    private final ProfileRepository profileRepository;

    public Profile findByUserId(String userId){ return profileRepository.findByUserId(userId);}
//
//    public Result<Profile> addProfile(AppUser user){
//
//    }
}
