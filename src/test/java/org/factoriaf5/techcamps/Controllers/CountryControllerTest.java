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
import org.factoriaf5.techcamps.models.Country;
import org.factoriaf5.techcamps.services.CountryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
  CountryService service;

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

    // System.out.println(response.getContentAsString()); 
    //System.out.println(mapper.writeValueAsString(countries)); 
    // Then
    assertThat(response.getStatus(), is(200));
    assertThat(response.getContentAsString(), containsString(spain.getName()));
    assertThat(response.getContentAsString(),containsString(australia.getName()));
    assertThat(response.getContentAsString(),equalTo(mapper.writeValueAsString(countries)));

    //[{"id":1,"name":"Spain"},{"id":2,"name":"Australia"}]
    //"[{\"id\":1,\"name\":\"Spain\"},{\"id\":2,\"name\":\"Australia\"}]"
  }
}
