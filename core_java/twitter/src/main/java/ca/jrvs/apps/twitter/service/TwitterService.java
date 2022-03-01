package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements Service{
  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {

    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet) ;
  }

  private void validatePostTweet(Tweet tweet) {
    String text = tweet.getText();
    if(text.length() > 140) {
      throw new RuntimeException("Text should be 140 characters or less");
    }
    if (tweet.getCoordinates() != null){
      double longitude = tweet.getCoordinates().getCoordinates().get(0);
      double latitude = tweet.getCoordinates().getCoordinates().get(1);
      if (longitude < -180 || longitude > 180 || latitude < -90 || latitude > 90){
        throw new RuntimeException("Twitter longitude or latitude invalid.");
      }
    }
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!id.matches("^[0-9]*$")){
      throw new RuntimeException("ID must be numbers only.");
    }
    String[] tweetFields = {"created_at", "id", "id_str", "text", "entities", "coordinates",
        "retweet_count", "favorite_count", "favorited", "retweeted"};

    if(fields != null) {
      for (String field : fields) {
        if (!Arrays.asList(tweetFields).contains(field)) {
          throw new RuntimeException("invalid field.");
        }
      }

        Tweet tweet = (Tweet) dao.findById(id);
        List<String> fieldsList = Arrays.asList(fields);

        if (!fieldsList.contains("created_at")){
          tweet.setCreated_at(null);
        }
        if (!fieldsList.contains("id")){
          tweet.setId(null);
        }
        if (!fieldsList.contains("id_str")){
          tweet.setId_str(null);
        }
        if (!fieldsList.contains("text")){
          tweet.setText(null);
        }
        if (!fieldsList.contains("entities")){
          tweet.setEntities(null);
        }
        if (!fieldsList.contains("coordinates")){
          tweet.setCoordinates(null);
        }
        if (!fieldsList.contains("retweet_count")){
          tweet.setRetweet_count(null);
        }
        if (!fieldsList.contains("favorite_count")){
          tweet.setFavorite_count(null);
        }
        if (!fieldsList.contains("favorited")){
          tweet.setFavorited(null);
        }
        if (!fieldsList.contains("retweeted")){
          tweet.setRetweeted(null);
        }
        return tweet;
    }

    return (Tweet) dao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids){
      if (!id.matches("^[0-9]*$")){
        throw new RuntimeException("tweet must be numbers only.");
      }
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }
}
