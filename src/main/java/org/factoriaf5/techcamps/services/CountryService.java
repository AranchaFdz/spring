package org.factoriaf5.techcamps.services;

//import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.repositories.CountryRespository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    
    private CountryRespository respository;

    
    public CountryService(CountryRespository respository) {
        this.respository = respository;
    }


    public List<Country> findAll() {

        /* List<Country> countries = new ArrayList<>();
        Country spain = new Country(1L, "Spain");
        Country cuba = new Country(2L, "Cuba");

        countries.add(spain);
        countries.add(cuba);

        return countries; */
        //return null;
        return respository.findAll();
    }
}
