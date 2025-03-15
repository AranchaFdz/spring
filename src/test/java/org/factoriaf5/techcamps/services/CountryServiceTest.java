package org.factoriaf5.techcamps.services;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @InjectMocks
    CountryService service;

    @Mock
    CountryRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new CountryService(repository);
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
        List<Country> list = service.findAll();

        // Then
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0).getName(), is("Spain"));
        assertThat(list.get(1).getName(), is("Australia"));
    }
}
