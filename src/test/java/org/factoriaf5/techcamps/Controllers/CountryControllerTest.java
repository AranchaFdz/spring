package org.factoriaf5.techcamps.Controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.techcamps.dtos.CountryDto;
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.services.CountryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CountryController.class)
@AutoConfigureMockMvc(addFilters = false) // disable Spring Security
public class CountryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  CountryServiceImpl service;

  @Autowired
  ObjectMapper mapper;

  @Test
  @DisplayName("Should return a list of countries")
  void testIndex() throws Exception {
    // Given
    // 1. Service = MockBean
    // 2. Mock HTTP = MockMvc
    // 3. List countries
    List<Country> countries = new ArrayList<>();
    Country spain = new Country(1L, "Spain");
    Country australia = new Country(2L, "Australia");
    countries.add(spain);
    countries.add(australia);
    // When
    when(service.findAll()).thenReturn(countries);
    
    // Llamar relizando llamada http mockeada
    MockHttpServletResponse response = mockMvc
      .perform(
        get("/api/v1/countries")
          .accept(MediaType.APPLICATION_JSON)
          .content("application/json"))
      .andExpect(status().is2xxSuccessful())
      .andExpect(status().isOk())
      .andReturn()
      .getResponse();

    //System.out.println(response.getContentAsString()); 
    //System.out.println(mapper.writeValueAsString(countries)); 
    // Then
    assertThat(response.getStatus(), is(200));
    assertThat(response.getContentAsString(), containsString(spain.getName()));
    assertThat(response.getContentAsString(),containsString(australia.getName()));
    assertThat(response.getContentAsString(),equalTo(mapper.writeValueAsString(countries)));

    //[{"id":1,"name":"Spain"},{"id":2,"name":"Australia"}]
    //"[{\"id\":1,\"name\":\"Spain\"},{\"id\":2,\"name\":\"Australia\"}]"
  }

  @Test
  @DisplayName("should store a country")
  void testStoreCountry() throws Exception {
    
    // Given
    Country country = new Country(1L, "New Zealand");
    String json = mapper.writeValueAsString(country);

    // When
    when(service.save(Mockito.any(CountryDto.class))).thenReturn(country);
    MockHttpServletResponse response = mockMvc.perform(post("/api/v1/countries")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .content(json))
      .andExpect(status().isCreated())
      .andReturn()
      .getResponse();

    // Then
    assertThat(response.getStatus(), is(201));
    assertThat(response.getContentAsString(), containsString(json));
    
  }

  @Test
    @DisplayName("Should return a certin country")
    void testFindCountryById() throws Exception {
        
        Country england = new Country(3L, "England");
        String json = mapper.writeValueAsString(england);

        when(service.getById(england.getId())).thenReturn(england);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries/3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(england.getName()));
        assertThat(response.getContentAsString(), containsString(json));
    }
    

}

  

  

