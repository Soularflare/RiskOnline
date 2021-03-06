package learn.risk_online.domain;

import learn.risk_online.data.AppUserRepository;
import learn.risk_online.data.ProfileRepository;
import learn.risk_online.models.AppUser;

import learn.risk_online.models.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final ProfileService profileService;
//    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository appUserRepository, ProfileRepository profileRepository, ProfileService profileService) {
        this.appUserRepository = appUserRepository;
        this.profileService = profileService;
//        this.encoder = encoder;
    }

    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }

    public AppUser findByUserID(String userID){
        return appUserRepository.findById(userID);
    }

    public AppUser findByUserName(String username) {return appUserRepository.findByUserName(username);}

    @Transactional
    public Result<AppUser> add(AppUser user){

        Result<AppUser> result = validateWithoutPassword(user);
        if(!result.isSuccess()){
            return result;
        }
        result = validatePassword(user);
        if(!result.isSuccess()){
            return result;
        }

        if(user.getUserId() != null){
            result.addErrorMessage("user id cannot be set for new user");
            return result;
        }
//
//        user.setPassword(encoder.encode(user.getPassword()));

        user = appUserRepository.add(user);
        if(user == null){
            result.addErrorMessage("user failed to create");
        }

        Result<Profile> pResult = profileService.addProfile(user.getUserId());
        if(!pResult.isSuccess()){
            for(String s : pResult.getMessages()){
                result.addErrorMessage(s);
            }
            return result;
        }

        result.setPayload(user); //maybe change to Profile?
        return result;
    }

    public Result<AppUser> update(AppUser user){
        Result<AppUser> result = validateWithoutPassword(user);
        if(!result.isSuccess()){
            return result;
        }

        if(user.getUserId() == null || user.getUserId().isBlank()){
            result.addErrorMessage("user id must be set for update");
            return result;
        }

        boolean success = appUserRepository.update(user);
        if(!success){
            result.addErrorMessage("user not updated");
        }
        return result;
    }

    public Result<AppUser> changePassword(AppUser user){
        Result<AppUser> result = validatePassword(user);
        if(!result.isSuccess()){
            return result;
        }
        if(user.getUserId() == null || user.getUserId().isBlank()){
            result.addErrorMessage("user id must be set to change password");
            return result;
        }
//        user.setPassword(encoder.encode(user.getPassword()));
        boolean success = appUserRepository.update(user);
        if(!success){
            result.addErrorMessage("password not updated");
        }
        return result;
    } //may want more checks maybe?

    public boolean deleteUser(String userId){
        return appUserRepository.deleteById(userId);
    }



    private Result<AppUser> validatePassword(AppUser user) {
        var result = new Result<AppUser>();

        if(user == null){
            result.addErrorMessage("user cannot be null");
            return result;
        }

        if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().length() < 8){
            result.addErrorMessage("password must be at least 8 characters");
            return result;
        }

        int digits = 0;
        int letters = 0;
        int others = 0;

        for(char c : user.getPassword().toCharArray()){
            if(Character.isDigit(c)){
                digits++;
            } else if (Character.isLetter(c)){
                letters++;
            } else {
                others++;
            }
        }

        if(digits == 0 || letters == 0 || others == 0){
            result.addErrorMessage("password must contain a digit, letter, and special character");
        }
        return result;
    }

    private Result<AppUser> validateWithoutPassword(AppUser user){
        Result<AppUser> result = new Result<AppUser>();

        if(user == null){
            result.addErrorMessage("user cannot be null");
            return result;
        }

        if(user.getUserName() == null || user.getUserName().isBlank()){
            result.addErrorMessage("username is required");
            return result;
        }

        var existing = appUserRepository.findByUserName(user.getUserName());
        if(existing != null && existing.getUserId() != user.getUserId()){
            result.addErrorMessage("username is already in use");
            return result;
        }
        return result;
    }
}
