package learn.risk_online.controllers;

import learn.risk_online.domain.Result;
import learn.risk_online.models.Adjacencyvalidator;
import learn.risk_online.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class AdjacencyValidatorController {

    private Adjacencyvalidator val = new Adjacencyvalidator();

    @PostMapping
    public ResponseEntity<List<Integer>> countriesForAttack(@RequestBody List<Integer> countries){
        val.setup();
        System.out.println("Countries are: "+ countries);

        List<Integer> toAttack = val.countriesToAttack(countries);
        if (toAttack != null && !toAttack.isEmpty()) {

            return new ResponseEntity<>(toAttack, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
