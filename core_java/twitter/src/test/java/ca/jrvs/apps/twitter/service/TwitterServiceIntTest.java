package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  private final static String CONSUMER_KEY = System.getenv("consumerKey");
  private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private final static String ACCESS_TOKEN = System.getenv("accessToken");
  private final static String TOKEN_SECRET = System.getenv("tokenSecret");
  private TwitterDao dao;
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    TwitterHttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    dao = new TwitterDao(httpHelper);
    service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {

  }

  @Test
  public void showTweet() {
  }

  @Test
  public void deleteTweets() {
  }
}