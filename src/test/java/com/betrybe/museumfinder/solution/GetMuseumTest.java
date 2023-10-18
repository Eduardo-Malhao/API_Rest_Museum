package com.betrybe.museumfinder.solution;


import static com.betrybe.museumfinder.evaluation.utils.TestHelpers.createMockMuseum;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GetMuseumTest {

  @MockBean
  private MuseumService museumService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Test Museum Service")
  void museumServiceTest() throws Exception {
    Museum museum = createMockMuseum(10L);

    Mockito
        .when(museumService.getMuseum(10L)).thenReturn(museum);

    mockMvc.perform(get("http://localhost:8080/museums/{id}", 10).
        accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(museumService).getMuseum(10L);
  }
}
