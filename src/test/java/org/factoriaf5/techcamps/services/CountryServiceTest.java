package org.factoriaf5.techcamps.services;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.factoriaf5.techcamps.dtos.CountryDto;
import org.factoriaf5.techcamps.exceptions.country.CountryNotFoundException;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @InjectMocks
    CountryServiceImpl service;

    @Mock
    CountryRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new CountryServiceImpl(repository);
    }
    @Test
    @DisplayName("Return all countries")
    void testFindAll() {
        // Given
        // 1. Repository
        // 2. Service
        // 3. model Country
        // 4. Spy - Mockito

        List<Country> countries = new ArrayList<>();
        Country spain = new Country(1L, "Spain");
        Country australia = new Country(2L, "Australia");
        countries.add(spain);
        countries.add(australia);
        // When
        when(repository.findAll()).thenReturn(countries);
        List<Country> list = service.findAll(); // Unit test

        // Then
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0).getName(), is("Spain"));
        assertThat(list.get(1).getName(), is("Australia"));
    }

    @Test
    void testSave() {

        // Given
        CountryDto dto = new CountryDto("France");
        Country france = new Country(2L, "America");

        // When
        when(repository.save(Mockito.any(Country.class))).thenReturn(france);
        Country country = service.save(dto);

        // Then
        //assertThat(country.getId(), equalTo(1L));
        assertThat(country, is(instanceOf(Country.class)));
        assertThat(country.getName(), equalTo("France"));
        
    }

    @Test
    @DisplayName("Should return a concret country using Id")
    void testGetById() {
        
        Country spain = new Country(3L, "Spain");

        when(repository.findById(spain.getId())).thenReturn(Optional.of(spain));
        Country country = service.getById(spain.getId());

        assertThat(country, instanceOf(Country.class));
        assertThat(country.getId(), is(spain.getId()));
        assertThat(country.getName(), is(spain.getName()));
        assertThat(country, is(equalToObject(spain)));
    }

    @Test
    @DisplayName("If country does not exist should return CountryNotFoundException")
    void testCountryNotFound() {

        String expected = "Country not found by id";
        
        CountryNotFoundException exception = assertThrows(CountryNotFoundException.class, () -> {
            service.getById(1L);
        });

        String msg = exception.getMessage();

        assertThat(msg, is(expected));
    }
}
