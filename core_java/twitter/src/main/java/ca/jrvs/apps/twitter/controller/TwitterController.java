package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterController implements Controller{
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException();
    }
    String text = args[1];
    String[] coordinates = args[2].split(COORD_SEP);
    double longitude = Double.parseDouble(coordinates[0]);
    double latitude = Double.parseDouble(coordinates[1]);

    return  null;
  }

  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }
}
