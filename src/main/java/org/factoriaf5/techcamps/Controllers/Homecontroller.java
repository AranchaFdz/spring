package org.factoriaf5.techcamps.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class Homecontroller {
    
    // GET
    @GetMapping("/")
    public String index() {
        return "Hello Spring & Spring Boot";
    }
    
}
