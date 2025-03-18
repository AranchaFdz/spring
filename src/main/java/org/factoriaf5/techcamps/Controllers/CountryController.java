package org.factoriaf5.techcamps.Controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.factoriaf5.techcamps.dtos.CountryDto;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

    @PostMapping("")
    public ResponseEntity<Country> postMethodName(@RequestBody CountryDto entity) {
    
        if (entity.name() =="") return ResponseEntity.badRequest().build();          
        
        Country country = service.save(entity);
        
        if (country == null) ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(country);
    }
    

}
