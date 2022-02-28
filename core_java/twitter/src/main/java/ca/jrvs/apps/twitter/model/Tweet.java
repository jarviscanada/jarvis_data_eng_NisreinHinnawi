package ca.jrvs.apps.twitter.model;

public class Tweet {
  private String created_at;

  private int id;
  private String id_str;
  private String text;
  private Entities entities;
  private Coordinates coordinates;
  private int retweet_count;
  private int favorite_count;
  private Boolean favorited;
  private Boolean retweeted;

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public void setRetweet_count(Integer retweet_count) {
    this.retweet_count = retweet_count;
  }

  public void setFavorite_count(Integer favorite_count) {
    this.favorite_count = favorite_count;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }

  public String getCreated_at() {
    return created_at;
  }

  public int getId() {
    return id;
  }

  public String getId_str() {
    return id_str;
  }

  public String getText() {
    return text;
  }

  public Entities getEntities() {
    return entities;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public int getRetweet_count() {
    return retweet_count;
  }

  public int getFavorite_count() {
    return favorite_count;
  }

  public Boolean getFavorited() {
    return favorited;
  }

  public Boolean getRetweeted() {
    return retweeted;
  }
}