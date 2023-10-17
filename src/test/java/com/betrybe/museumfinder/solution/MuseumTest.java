package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
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
public class MuseumTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CollectionTypeService collectionService;

  @Test
  @DisplayName("Requisicao BAD")
  void getCollection404() throws Exception {
    Mockito
        .when(collectionService.countByCollectionTypes("xablau"))
        .thenReturn(new CollectionTypeCount( new String[] {"xablau"}, 0));

    mockMvc.perform(get("http://localhost:8080/collections/count/xablau").accept(MediaType.APPLICATION_JSON)).
      andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Requisicao OK")
  void getCollectionOk() throws Exception {
    Mockito
        .when(collectionService.countByCollectionTypes("louvre"))
        .thenReturn(new CollectionTypeCount( new String[] {"louvre"}, 1));

    mockMvc.perform(get("http://localhost:8080/collections/count/louvre").accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk());
  }
}
