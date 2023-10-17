package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
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

  /**
   * Java Doc Method.
   */
  @GetMapping("closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam("lat") Double latitude,
      @RequestParam("lng") Double longitude,
      @RequestParam("max_dist_km") Double maxDistanceKm
  ) {

    Coordinate coordinate = new Coordinate(latitude, longitude);

    Museum getMuseum = museumService.getClosestMuseum(coordinate, maxDistanceKm);
    if (getMuseum == null) {
      return ResponseEntity.notFound().build();
    } else {
      MuseumDto museumDto = modelToDto(getMuseum);
      return ResponseEntity.status(HttpStatus.OK).body(museumDto);
    }
  }

  private MuseumDto modelToDto(Museum model) {
    return new MuseumDto(
        model.getId(),
        model.getName(),
        model.getDescription(),
        model.getAddress(),
        model.getCollectionType(),
        model.getSubject(),
        model.getUrl(),
        model.getCoordinate()
    );
  }
}
