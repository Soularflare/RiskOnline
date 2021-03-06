package learn.risk_online.data;

import learn.risk_online.models.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> findAll(int gameId);

    Country findById(int gameId, int countryId);

    boolean add(Country country);

    boolean update(Country country);

    boolean deleteAllById(int gameId);
}
