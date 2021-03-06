package learn.risk_online.controllers;

import learn.risk_online.domain.AppUserService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/appuser")
public class AppUserController {
    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> findByUserData(@RequestBody AppUser user){
            AppUser existing = service.findByUserName(user.getUserName());

        if(existing == null){
            return new ResponseEntity<>("username or password invalid", HttpStatus.NOT_FOUND);
        }
        if(!user.getPassword().equals(existing.getPassword())){
            return new ResponseEntity<>("username or password invalid", HttpStatus.NOT_FOUND);
        }
        existing.setPassword("");

        return new ResponseEntity<>(existing, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addUser(@RequestBody AppUser user){
        Result<AppUser> result = service.add(user);
        if (result.isSuccess()) {
            user = result.getPayload();
            user.setPassword("");
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }
}
