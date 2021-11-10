package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import learn.risk_online.models.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CountryServiceTest {

    @Autowired
    CountryService service;

    @MockBean
    CountryRepository countryRepository;


    List<Country> countries = List.of(new Country(1, 0, "C", 0, 2),
            new Country(1,1, "C", 0, 2), new Country(1,2, "C", 0, 2),
            new Country(1,3, "C", 0, 2), new Country(1,4, "C", 0, 2),
            new Country(1,5, "C", 0, 2), new Country(1,6, "C", 0, 2),
            new Country(1,7, "C", 0, 2), new Country(1,8, "C", 0, 2),
            new Country(1,9, "C", 0, 2), new Country(1,10, "C", 0, 2),
            new Country(1,11, "C", 0, 2), new Country(1,12, "C", 0, 2),
            new Country(1,13, "C", 0, 2), new Country(1,14, "C", 0, 2),
            new Country(1,15, "C", 0, 2), new Country(1,16, "C", 0, 2),
            new Country(1,17, "C", 0, 2), new Country(1,18, "C", 0, 2),
            new Country(1,19, "C", 0, 2), new Country(1,20, "C", 0, 2),
            new Country(1,21, "C", 0, 2), new Country(1,22, "C", 0, 2),
            new Country(1,23, "C", 0, 2), new Country(1,24, "C", 0, 2),
            new Country(1,25, "C", 0, 2), new Country(1,26, "C", 0, 2),
            new Country(1,27, "C", 0, 2), new Country(1,28, "C", 0, 2),
            new Country(1,29, "C", 0, 2), new Country(1,30, "C", 0, 2),
            new Country(1,31, "C", 0, 2), new Country(1,32, "C", 0, 2),
            new Country(1,33, "C", 0, 2), new Country(1,34, "C", 0, 2),
            new Country(1,35, "C", 0, 2), new Country(1,36, "C", 0, 2),
            new Country(1,37, "C", 0, 2), new Country(1,38, "C", 0, 2),
            new Country(1,39, "C", 0, 2), new Country(1,40, "C", 0, 2),
            new Country(1,41, "C", 1, 2));


    @Test
    void shouldAddCountries() {
        when(countryRepository.add(any())).thenReturn(true);

        Result<Country> expected = new Result<>();
        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddNullCountries() {
        List<Country> list = null;

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Countries are required to save game");

        Result<Country> actual = service.addCountries(list);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddTooFewCountries() {
        List<Country> list = List.of(new Country(1,29, "C", 0, 2));

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("All 42 countries required to save game");

        Result<Country> actual = service.addCountries(list);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddInvalidId() {
        for(Country c : countries){
            c.setGameId(0);
        }

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Valid game id is required");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddDuplicateCountries() {
        for(Country c : countries){
            c.setCountryId(0);
        }

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Countries cannot be duplicated");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotHaveMultipleGameIds() {
        for(int x = 0; x < 5; x++){
            countries.get(x).setGameId(4);
        }

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Countries must have same game id");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotHaveOnePlayerInPossession() {
        countries.get(41).setPlayerPossession(0);

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("At least 2 players must be in possession of countries");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotHaveValidPlayerPossession() {
        countries.get(41).setPlayerPossession(7);

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Valid player possession required");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotHaveValidArmy() {
        countries.get(41).setArmy(0);

        Result<Country> expected = new Result<>();
        expected.addErrorMessage("Invalid army count");

        Result<Country> actual = service.addCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }


    @Test
    void shouldUpdateCountries() {
        when(countryRepository.update(any())).thenReturn(true);

        Result<Country> expected = new Result<>();
        Result<Country> actual = service.updateCountries(countries);
        assertEquals(expected.getMessages(), actual.getMessages());
    }

}