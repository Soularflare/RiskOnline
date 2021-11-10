package learn.risk_online.controllers;

import learn.risk_online.domain.ProfileService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public Profile findByUserId(@PathVariable String userId){
        return service.findByUserId(userId);
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
