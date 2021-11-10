package learn.risk_online.domain;

import learn.risk_online.data.ProfileMicrotransactionRepository;
import learn.risk_online.models.ProfileMicrotransaction;
import org.springframework.stereotype.Service;

@Service
public class ProfileMicrotransactionService {

    private final ProfileMicrotransactionRepository repository;


    public ProfileMicrotransactionService(ProfileMicrotransactionRepository repository) {
        this.repository = repository;
    }

    public Result<ProfileMicrotransaction> add(ProfileMicrotransaction proMicro){
        Result<ProfileMicrotransaction> result = validate(proMicro);
        if(!result.isSuccess()){
            return result;
        }
        if(!repository.add(proMicro)){
            result.addErrorMessage("failed to add microtransaction to profile");
        }
        return result;
    }

    public Result<ProfileMicrotransaction> update(ProfileMicrotransaction proMicro){
        Result<ProfileMicrotransaction> result = validate(proMicro);
        if(!result.isSuccess()){
            return result;
        }
        if(!repository.update(proMicro)){
            result.addErrorMessage("failed to update microtransaction on profile");
        }
        return result;
    }

    private Result<ProfileMicrotransaction> validate(ProfileMicrotransaction proMicro) {
        Result<ProfileMicrotransaction> result = new Result<>();
        if(proMicro == null){
            result.addErrorMessage("Profile microtransaction required to add or update");
            return result;
        }
        if(proMicro.getProfileId() == null){
            result.addErrorMessage("Profile id required to add microtransaction to profile " +
                    "or update microtransaction on profile");
            return result;
        }
        if(proMicro.getProfileId().isBlank()){
            result.addErrorMessage("Profile id required to add microtransaction to profile " +
                    "or update microtransaction on profile");
            return result;
        }
        if(proMicro.getMicrotransaction() == null){
            result.addErrorMessage("Microtransaction required to add to profile " +
                    "or update microtransaction on profile");
            return result;
        }
        if(proMicro.getMicrotransaction().getId() < 1){
            result.addErrorMessage("Valid microtransaction id required to add to profile " +
                    "or update microtransactionn on profile");
            return result;
        }

        return result;
    }
}
