package com.betrybe.museumfinder.service;

import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import java.util.Optional;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betrybe.museumfinder.database.MuseumFakeDatabase;

/**
 *  Java Type Method
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private MuseumFakeDatabase museumDB;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumDatabase) {
    this.museumDatabase = museumDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double km) {
    if (!isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

  Optional<Museum> closestMuseumOptional = museumDatabase
      .getClosestMuseum(coordinate, maxDistance);

    if (museum.isEmpty()) {
    throw new MuseumNotFoundException();
  }
    retrun
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (isCoordinateValid(museum.getCoordinate())) {
      return museumDB.saveMuseum(museum);
    }
    throw new InvalidCoordinateException();
  }
}