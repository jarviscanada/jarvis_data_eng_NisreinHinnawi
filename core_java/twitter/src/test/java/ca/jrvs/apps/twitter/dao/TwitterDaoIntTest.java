package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");

  private TwitterDao dao;

  @Before
  public void setUp() throws Exception {
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() {
    String text ="No one can make you feel inferior without your consent2";
    Double longitude = 1d;
    Double latitude = -1d;
    List<Double> coordinatesList = new ArrayList<>();
    coordinatesList.add(longitude);
    coordinatesList.add(latitude);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(coordinatesList);

    Tweet post = new Tweet();
    post.setCoordinates(coordinates);
    post.setText(text);

    dao.create(post);

    assertEquals(longitude, post.getCoordinates().getCoordinates().get(0));
    assertEquals(latitude, post.getCoordinates().getCoordinates().get(1));
    assertEquals(text, post.getText());

  }

  @Test
  public void findById() {
    String postID = "1498379253892714505";
    String expectedText ="No one can make you feel inferior without your consent";
    Tweet tweet = dao.findById(postID);
    Assert.assertEquals(expectedText, tweet.getText());
    Assert.assertEquals(postID, tweet.getId_str());
  }

  @Test
  public void deleteById() {
    String postID = "1498702274524295169";
    Tweet tweet = dao.deleteById(postID);
    Assert.assertEquals(postID, tweet.getId_str());

  }
}