package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Java Doc Method.
 */

@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumService museumService;

  @Autowired
  public MuseumController(MuseumService museumService) {
    this.museumService = museumService;
  }

  /**
   * Java Doc Method.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {

    Museum newMuseum = converter(museumDto);

    Museum createNewMuseum = museumService.createMuseum(newMuseum);

    return ResponseEntity.status(HttpStatus.CREATED).body(createNewMuseum);
  }

  private Museum converter(MuseumCreationDto data) {
    Museum museumData = new Museum();

    museumData.setName(data.name());
    museumData.setDescription(data.description());
    museumData.setAddress(data.address());
    museumData.setCollectionType(data.collectionType());
    museumData.setSubject(data.subject());
    museumData.setUrl(data.url());
    museumData.setCoordinate(data.coordinate());

    return museumData;
  }

}
