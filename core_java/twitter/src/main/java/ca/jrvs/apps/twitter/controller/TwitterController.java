package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
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
   if(args.length != 3) {
     throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
   }
    String text = args[1];
    String coord = args[2];
    String[] coordArr = coord.split(COORD_SEP);
    List<Double> coordList = new ArrayList<>();

    for (String c : coordArr) {
      coordList.add(Double.parseDouble(c));
    }
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(coordList);
    tweet.setCoordinates(coordinates);

    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if(args.length < 2) {
      throw new IllegalArgumentException("Tweet ID must be provided");
    }
    String tweetID = args[1];
    String[] fields;
    if (args.length == 3) {
      fields = args[2].split(COMMA);
    } else {
      fields = null;
    }

    return service.showTweet(tweetID, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Tweet ID must be provided");
    }
    String[] tweetID = args[1].split(COMMA);
    return service.deleteTweets(tweetID);  }
}
