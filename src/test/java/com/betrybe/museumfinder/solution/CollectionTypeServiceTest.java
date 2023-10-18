package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  CollectionTypeService collectionService;

  @Test
  @DisplayName("Teste na Service")
  public void CollectionTypesService() {
    Mockito.when(museumFakeDatabase.countByCollectionType(any())).thenReturn(150L);

    CollectionTypeCount collectionTypeCount = collectionService.countByCollectionTypes(
        "vasos");
    String collectionType = collectionTypeCount.collectionTypes()[0];

    assertNotNull(collectionTypeCount);
    assertEquals("vasos", collectionType);
    assertEquals(150L, collectionTypeCount.count());

    Mockito.verify(museumFakeDatabase).countByCollectionType(eq("vasos"));
  }
}
