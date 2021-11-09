package learn.risk_online.controllers;

import learn.risk_online.domain.AppUserService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

//    @GetMapping("/{username}/{password}")
//    public ResponseEntity<Object> findByUserData(@PathVariable String username, @PathVariable String password){
//
//        if(service.findByUserID(username))
//
//        if (service.findByUserData(username, password)) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

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
