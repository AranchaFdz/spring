package org.factoriaf5.techcamps.Controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping(path = "${api-endpoint}/countries")
public class CountryController {

    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Country> index() {

        return service.findAll();
    }

}
