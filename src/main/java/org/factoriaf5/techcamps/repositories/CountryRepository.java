package org.factoriaf5.techcamps.repositories;

import org.factoriaf5.techcamps.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long>{
    
}
