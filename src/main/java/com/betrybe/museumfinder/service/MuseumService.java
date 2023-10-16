package com.betrybe.museumfinder.service;

//import com.betrybe.museumfinder.util.CoordinateUtil;
//import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.model.Museum;
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
  public Museum createMuseum(Museum museum) {
    if (isCoordinateValid(museum.getCoordinate())) {
      return museumDB.saveMuseum(museum);
    }
    throw new InvalidCoordinateException();
  }
}