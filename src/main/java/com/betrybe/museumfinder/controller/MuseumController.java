package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  private final MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Java Doc Method.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {

    Museum converter = ModelDtoConverter.dtoToModel(museumDto);

    Museum createNewMuseum = museumService.createMuseum(converter);

    return ResponseEntity.status(HttpStatus.CREATED).body(createNewMuseum);
  }

  /**
   * Java Doc Method.
   */
  @GetMapping("/closest")
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
      MuseumDto converter = ModelDtoConverter.modelToDto(getMuseum);
      return ResponseEntity.status(HttpStatus.OK).body(converter);
    }
  }

  /**
   * Java Doc Method.
   */
  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getById(@PathVariable Long id) {

    Museum findMuseum = museumService.getMuseum(id);
    if (findMuseum == null) {
      return ResponseEntity.notFound().build();
    } else {
      MuseumDto converter = ModelDtoConverter.modelToDto(findMuseum);
      return  ResponseEntity.status(HttpStatus.OK).body(converter);
    }
  }
}
