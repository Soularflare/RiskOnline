package learn.risk_online.domain;

import learn.risk_online.data.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    private final CountryRepository countryRepository;
}
