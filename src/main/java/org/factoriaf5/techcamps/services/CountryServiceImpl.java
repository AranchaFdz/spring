package org.factoriaf5.techcamps.services;

import java.util.List;

import org.factoriaf5.techcamps.dtos.CountryDto;
import org.factoriaf5.techcamps.exceptions.country.CountryNotFoundException;
import org.factoriaf5.techcamps.implementations.IGenericService;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.repositories.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements IGenericService<Country, CountryDto>{
    
    private CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country save(CountryDto countryDto) {
        Country country = new Country(countryDto.name());
        repository.save(country);
        return country;
    }

     /**
     * @param id
     * @return
     * 
     * Exceptions:
     * 
     * Types:
     * 1. Checked exceptions -> se comprueban en tiempo de compilación y se usan con un try-catch
     * a. IOException
     * b. FileNotFoundException
     * c. ParseException
     * d. SQLException
     * e. ClassNotFoundException
     * f. InterruptedException
     * i. MalformedURLException
     * 
     * 2. Errors -> fuera de control del developer
     * 
     * 3. Unchecked Exceptions -> no se comprueban en tiempo de compilación sino en tiempo de ejecución. no se necesita un try-catch. Se heredan de la clase RuntimeException.
     * 
     * link: https://www.tpointtech.com/list-of-checked-exceptions-in-java
     * 
     */
    @Override
    public Country getById(Long id) {
        Country country = repository.findById(id).orElseThrow(()-> new CountryNotFoundException("Country not found by id"));
        return country;
    }


}
