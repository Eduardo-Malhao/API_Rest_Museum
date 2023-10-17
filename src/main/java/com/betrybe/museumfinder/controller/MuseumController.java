package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.service.MuseumService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/museums")
public class MuseumController {

  private  MuseumService museumService;

  @Autowired
  public MuseumController(MuseumService museumService) {
    this.museumService = museumService;
  }

  /**
   * Java Doc Method.
   */
  @PostMapping
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto museumDto) {

    newMuseum = museumService.createMuseum(museumDto);

    MuseumDto museumDto = new MuseumDto(
        newMuseum.getId(),
        newMuseum.getName(),
        newMuseum.getDescription(),
        newMuseum.getAddress(),
        newMuseum.getCollectionType(),
        newMuseum.getSubject(),
        newMuseum.getUrl(),
        newMuseum.getCoordinate()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(museumDto);
  }
}
