package org.factoriaf5.techcamps.services;

import java.util.List;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.repositories.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    
    private CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }


    public List<Country> findAll() {
        return repository.findAll();
    }
}
