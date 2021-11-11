package learn.risk_online.controllers;

import learn.risk_online.domain.MicrotransactionService;
import learn.risk_online.domain.ProfileMicrotransactionService;
import learn.risk_online.domain.ProfileService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.Microtransaction;
import learn.risk_online.models.Profile;
import learn.risk_online.models.ProfileMicrotransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/profile")
public class ProfileMicrotransactionController {

    private final ProfileMicrotransactionService service;
    private final ProfileService profileService;
    private final MicrotransactionService microtransactionService;

    public ProfileMicrotransactionController(ProfileMicrotransactionService service, ProfileService profileService, MicrotransactionService microtransactionService) {
        this.service = service;
        this.profileService = profileService;
        this.microtransactionService = microtransactionService;
    }

    @Transactional
    @PostMapping("/{microId}/{userId}")
    public ResponseEntity<Object> add(@PathVariable int microId, @PathVariable String userId) {

        Profile profile = profileService.findByUserId(userId);
        if(profile == null){
            return new ResponseEntity<>("failed to find profile associated with user", HttpStatus.BAD_REQUEST);
        }
        Microtransaction microtransaction = microtransactionService.findById(microId);
        if(microtransaction == null){
            return new ResponseEntity<>("failed to find microtransaction", HttpStatus.BAD_REQUEST);
        }
        List<ProfileMicrotransaction> microtransactionList = profile.getMicrotransactions();
        for(ProfileMicrotransaction pMicrotransaction : microtransactionList){
            pMicrotransaction.setEquipped(false);
        }

        ProfileMicrotransaction pm = new ProfileMicrotransaction();
        pm.setProfileId(profile.getProfileId());
        pm.setMicrotransaction(microtransaction);
        pm.setEquipped(true);

        for(ProfileMicrotransaction pMicrotransaction : microtransactionList){
            Result<ProfileMicrotransaction> result = service.update(pMicrotransaction);
            if(!result.isSuccess()){
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        Result<ProfileMicrotransaction> result = service.add(pm);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/equip/{microId}/{userId}")
    public ResponseEntity<Object> update(@PathVariable int microId, @PathVariable String userId) {

        Profile profile = profileService.findByUserId(userId);
        if(profile == null){
            return new ResponseEntity<>("failed to find profile associated with user", HttpStatus.BAD_REQUEST);
        }
        List<ProfileMicrotransaction> microtransactionList = profile.getMicrotransactions();

        for(ProfileMicrotransaction pMicrotransaction : microtransactionList){
            pMicrotransaction.setEquipped(false);
            if(pMicrotransaction.getMicrotransaction().getId() == microId){
                pMicrotransaction.setEquipped(true);
            }
            Result<ProfileMicrotransaction> result = service.update(pMicrotransaction);
            if(!result.isSuccess()){
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
