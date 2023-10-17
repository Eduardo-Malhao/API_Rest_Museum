package com.betrybe.museumfinder.service;

import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Java Type Method.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private MuseumFakeDatabase museumFakeDb;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumDatabase) {
    this.museumFakeDb = museumDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double km) {
    if (!isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> closestMuseum = museumFakeDb.getClosestMuseum(coordinate, km);

    if (closestMuseum.isPresent()) {
      retrun closestMuseum.get();
    }
    throw new MuseumNotFoundException();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (isCoordinateValid(museum.getCoordinate())) {
      return museumFakeDb.saveMuseum(museum);
    }
    throw new InvalidCoordinateException();
  }
}