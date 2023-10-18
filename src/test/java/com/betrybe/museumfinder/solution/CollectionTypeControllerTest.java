package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
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
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CollectionTypeService collectionService;
  MuseumService museumService;

  @Test
  @DisplayName("Requisicao BAD")
  void getCollection404() throws Exception {
    Mockito
        .when(collectionService.countByCollectionTypes("xablau"))
        .thenReturn(new CollectionTypeCount( new String[] {"xablau"}, 0L));

    mockMvc.perform(get("http://localhost:8080/collections/count/xablau").accept(MediaType.APPLICATION_JSON)).
      andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Requisicao OK")
  void getCollectionOk() throws Exception {
    Mockito
        .when(collectionService.countByCollectionTypes("louvre"))
        .thenReturn(new CollectionTypeCount( new String[] {"louvre"}, 1L));

    mockMvc.perform(get("http://localhost:8080/collections/count/louvre").accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk());
  }
//
//  @Test
//  @DisplayName("Requisicao OK 2 termos")
//  void getCollection2Terms() throws Exception {
//    Mockito
//        .when(collectionService.countByCollectionTypes("louvre", "museum")
//        .thenReturn(new CollectionTypeCount(new String[] {"history", "math"}, 2L));
//
//    mockMvc.perform(get("/collections/count/history,math").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//fazer busca para 2 valores

//  @Test
//  @DisplayName("Requisicao OK - Museu encontrado")
//  void getMuseumByIdFound() throws Exception {
//    Long id = 1L;
//    Museum museum = new Museum();
//    Mockito
//      .when(museumService.getMuseum(id))
//        .thenReturn(Optional.of(museum));
//
//    mockMvc.perform(get("http://localhost:8080/museums/{id}", id).accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  @DisplayName("Requisicao - Museu nao encontrado")
//  void getMuseumByIdNotFound() throws Exception {
//    Long id = 2L;
//    Mockito
//    .when(museumService.getMuseum(id))
//        .thenReturn(Optional.empty());
//
//    mockMvc.perform(get("http://localhost:8080/museums/{id}", id).accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isNotFound());
//  }
}
