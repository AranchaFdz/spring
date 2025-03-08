package org.factoriaf5.techcamps.repositories;

import org.factoriaf5.techcamps.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRespository extends JpaRepository<Country, Long>{
    
}
