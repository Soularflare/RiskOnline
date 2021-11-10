package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import learn.risk_online.models.Country;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    private final CountryRepository countryRepository;

    public List<Country> findCountriesWithGameId(int gameId){
        return countryRepository.findAll(gameId);
    }

    @Transactional
    public Result<Country> addCountries(List<Country> countries){
        Result<Country> result = new Result<>();
        if(countries == null){
            result.addErrorMessage("Countries are required to save game");
            return result;
        }
        if(countries.size() == 0) {
            result.addErrorMessage("Countries are required to save game");
            return result;
        }
        for(Country country : countries){
            result = validateCountries(country, countries);
            if(!result.isSuccess()){
                return result;
            }
        }
        for(Country country : countries){
            if(!countryRepository.add(country)){
                result.addErrorMessage("failed to add countries");
                return result;
            }
        }
        return result;
    }

    @Transactional
    public Result<Country> updateCountries(List<Country> countries){
        Result<Country> result = new Result<>();

        if(countries == null){
            result.addErrorMessage("Countries are required to save game");
            return result;
        }
        if(countries.size() == 0) {
            result.addErrorMessage("Countries are required to save game");
            return result;
        }

        for(Country country : countries){
            result = validateCountries(country, countries);
            if(!result.isSuccess()){
                return result;
            }
        }
        for(Country country : countries){
            if(!countryRepository.update(country)){
                result.addErrorMessage("failed to update countries");
                return result;
            }
        }
        return result;
    }

    private Result<Country> validateCountries(Country country, List<Country> countries) {
        Result<Country> result = new Result<>();

        if(countries.size() < 42){
            result.addErrorMessage("All 42 countries required to save game");
        }

        if(countries.size() > 42){
            result.addErrorMessage("Too many countries in list");
        }

        if(!result.isSuccess()){
            return result;
        }

        if(country == null){
            result.addErrorMessage("Country cannot be null");
            return result;
        }

        if(country.getGameId() < 1){
            result.addErrorMessage("Valid game id is required");
        }

        if(country.getCountryId() < 0 || country.getCountryId() > 41){
            result.addErrorMessage("Valid country id is required");
        }

        if(countries.stream()
                .filter(c -> c.getCountryId() == country.getCountryId())
                .collect(Collectors.toList()).size() > 1){
            result.addErrorMessage("Countries cannot be duplicated");
        }

        if(!countries.stream().allMatch(c -> c.getGameId() == country.getGameId())){
            result.addErrorMessage("Countries must have same game id");
        }

        if(countries.stream().allMatch(c -> c.getPlayerPossession() == country.getPlayerPossession())){
            result.addErrorMessage("At least 2 players must be in possession of countries");
        }

        if(country.getPlayerPossession() < 0 || country.getPlayerPossession() >= 6){
            result.addErrorMessage("Valid player possession required");
        }

        if(country.getArmy() < 1){
            result.addErrorMessage("Invalid army count");
        }

        return result;
    }
}

