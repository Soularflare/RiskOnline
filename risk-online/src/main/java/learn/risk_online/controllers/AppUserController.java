package learn.risk_online.controllers;

import learn.risk_online.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class AppUserController {
//    private final AppUserService service;

//    public AppUserController(AppUserService service) {
//        this.service = service;
//    }

//    @GetMapping("/{username}/{password}")
//    public ResponseEntity<Object> findByUserData(@PathVariable String username, @PathVariable String password){
//
//        if (service.findByUserData(username, password)) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @PostMapping("/{username}/{password}")
//    public ResponseEntity<Object> AddUser(@PathVariable String username, @PathVariable String password){
//        Result<AppUser> result = service.AddUser(username, password);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//        }
//        return ErrorResponse.build(result);
//    }
}
