package org.factoriaf5.techcamps.Controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CountryController {

    // @Autowired
    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/countries")
    public List<Country> index() {

        return service.findAll();
        /*
         * List<Country> countries = new ArrayList<>();
         * Country spain = new Country(1L, "Spain");
         * Country cuba = new Country(2L, "Cuba");
         * 
         * countries.add(spain);
         * countries.add(cuba);
         * 
         * return countries;
         */
    }

}
