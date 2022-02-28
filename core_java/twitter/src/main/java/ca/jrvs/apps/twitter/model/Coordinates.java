package ca.jrvs.apps.twitter.model;

import java.util.List;

public class Coordinates {
  private String type;
  private List<Double> coordinates;

  public void setType(String type) {
    this.type = type;
  }

  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public List<Double> getCoordinates() {
    return coordinates;
  }
}
