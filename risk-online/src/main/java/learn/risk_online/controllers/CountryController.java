package learn.risk_online.controllers;

import learn.risk_online.domain.CountryService;
import learn.risk_online.domain.Result;
import learn.risk_online.models.Country;
import learn.risk_online.models.Game;
import learn.risk_online.models.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{gameId}")
    public List<Country> findByGameId(@PathVariable int gameId){
        return countryService.findCountriesWithGameId(gameId);
    }

//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody List<Country> countries){
//        Result<Country> result = countryService.addCountries(countries);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
//    }
//
//    @PutMapping("/{gameId}")
//    public ResponseEntity<Object> update(@PathVariable int gameId, @RequestBody List<Country> countries){
//        for(Country c : countries){
//            if(gameId != c.getGameId()){
//                return new ResponseEntity<>(HttpStatus.CONFLICT);
//            }
//        }
//
//        Result<Country> result = countryService.updateCountries(countries);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
//    }
}
