package learn.risk_online.controllers;

import learn.App;
import learn.risk_online.domain.AppUserService;
import learn.risk_online.domain.ProfileService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService service;
    private final AppUserService appUserService;

    public ProfileController(ProfileService service, AppUserService appUserService) {
        this.service = service;
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<Object> findByUserId(@RequestBody AppUser user){
        AppUser found = appUserService.findByUserName(user.getUserName());
        if(found == null){
            return new ResponseEntity<>("failed to find user", HttpStatus.BAD_REQUEST);
        }
        Profile profile = service.findByUserId(found.getUserId());
        if(profile == null){
            return new ResponseEntity<>("failed to find profile", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/profile/points")
    private ResponseEntity<Object> fetchPoints(@RequestBody AppUser user){
        AppUser found = appUserService.findByUserName(user.getUserName());
        if(found == null){
            return new ResponseEntity<>("failed to find user", HttpStatus.BAD_REQUEST);
        }
        Profile profile = service.findByUserId(found.getUserId());
        if(profile == null){
            return new ResponseEntity<>("failed to find profile", HttpStatus.BAD_REQUEST);
        }
        int points = profile.getPoints();

        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @PutMapping("/update/points/{num}")
    private ResponseEntity<Object> savePoints(@PathVariable int num, @RequestBody AppUser user){
        AppUser found = appUserService.findByUserName(user.getUserName());
        if(found == null){
            return new ResponseEntity<>("failed to find user", HttpStatus.BAD_REQUEST);
        }
        Profile profile = service.findByUserId(found.getUserId());
        if(profile == null){
            return new ResponseEntity<>("failed to find profile", HttpStatus.BAD_REQUEST);
        }
        profile.setPoints(num);
        Result<Profile> result = service.updateProfile(profile);
        if(!result.isSuccess()){
            return new ResponseEntity<>("failed to update points", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> update(@PathVariable String userId, @RequestBody Profile profile){
        if(userId != profile.getUserId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Profile> result = service.updateProfile(profile);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }

}
