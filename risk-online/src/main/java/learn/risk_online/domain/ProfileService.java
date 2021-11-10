package learn.risk_online.domain;

import learn.risk_online.data.ProfileRepository;
import learn.risk_online.models.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMicrotransactionService proMicroService;

    public ProfileService(ProfileRepository profileRepository, ProfileMicrotransactionService proMicroService) {
        this.profileRepository = profileRepository;
        this.proMicroService = proMicroService;
    }

    public Profile findByUserId(String userId){ return profileRepository.findByUserId(userId);}

    public Result<Profile> addProfile(String userId){
        Result<Profile> result = new Result<>();

        if(userId == null){
            result.addErrorMessage("user id is required to create profile");
            return result;
        }
        Profile profile = profileRepository.add(userId);
        if(profile == null){
            result.addErrorMessage("failed to create profile");
            return result;
        }
        result.setPayload(profile);
        return result;
    }

    public Result<Profile> updateProfile(Profile profile){
        Result<Profile> result = validate(profile);
        if(!result.isSuccess()){
            return result;
        }
        if(!profileRepository.updateByUserId(profile)){
            result.addErrorMessage("failed to update profile");
        }
        //add proMicro with service or update proMicro
        return result;
    }

    public boolean deleteByUserId(String userId){
        return (profileRepository.deleteByUserId(userId));
    }



    private Result<Profile> validate(Profile profile) {
        Result<Profile> result = new Result<>();

        if(profile == null){
            result.addErrorMessage("profile is required to update");
            return result;
        }

        if(profile.getUserId() == null){
            result.addErrorMessage("user id is required to update");
            return result;
        }
//        if(profile.getProfileId() == null){
//            result.addErrorMessage("profile id is required to update");
//            return result;
//        }

        if(profile.getTotalGames() < 0 || profile.getGameTime() < 0 || profile.getWins() < 0){
            result.addErrorMessage("Profile stats cannot be less than 0");
        }
        return result;
    }


}
