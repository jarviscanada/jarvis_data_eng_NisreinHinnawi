package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet,String>{

  //URI constraints
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Expected response code
  private static final int HTTP_OK = 200;
  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao (HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }


  @Override
  public Tweet create(Tweet entity) {
    String text = entity.getText();
    Coordinates coordinates = entity.getCoordinates();
    double longitude = coordinates.getCoordinates().get(0);
    double latitude = coordinates.getCoordinates().get(1);
    PercentEscaper percentEscaper = new PercentEscaper("", false);

    URI uri = URI.create(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(text) +
        AMPERSAND + "long" + EQUAL + longitude + AMPERSAND + "lat" + EQUAL + latitude);

    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String s) {
    URI uri = URI.create(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);
    HttpResponse response = httpHelper.httpGet(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    URI uri = URI.create(API_BASE_URI + DELETE_PATH + "/" + s + ".json");
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }


  private Tweet parseResponseBody(HttpResponse response, int statusCode) {
    Tweet tweet = null;
    int status = response.getStatusLine().getStatusCode();

    if (status!= HTTP_OK) {
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }
    if (response.getEntity() == null){
      throw new RuntimeException("Response has no Entities.");
    }
    try {
      String str = EntityUtils.toString(response.getEntity());
      tweet = JsonParser.toObjectFromJson(str, Tweet.class);
    } catch (IOException e){
      throw new RuntimeException("Unable to convert JSON string to Object", e);
    }
    return tweet;
  }

}
