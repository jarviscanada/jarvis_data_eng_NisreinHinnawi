package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class TwitterApiTest {
  private static String CONSUMER_KEY = System.getenv("YGeLzdNcwkWt5pPOD7amY4V3x");
  private static String CONSUMER_SECRET = System.getenv("Y3vRwbyPibCGhsjZgMqADGKXpcHuBkUPl0fWeTpK5Bwif9rIIe");
  private static String ACCESS_TOKEN = System.getenv("1007602087780286464-dhMNCmJMtCYK7msMzSxfiLD1p4bLQd");
  private static String TOKEN_SECRET = System.getenv("5qpps1KsVGWlNN9z9GAiDKAeUMIFa5ZqvOLWoj76gAGsZ");

  public static void main(String[] args) throws Exception{
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    String status = "today is a good day";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/lookup.json?screen_name=" + percentEscaper.escape(status));

    consumer.sign(request);

    System.out.println("Http Request Headers:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));

  }
}