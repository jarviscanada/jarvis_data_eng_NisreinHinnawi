package ca.jrvs.apps.twitter.model;

import java.util.List;

public class UserMention {
  private String name;
  private List<Integer> indices;
  private String screen_name;
  private int id;
  private String id_str;

  public void setName(String name) {
    this.name = name;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  public String getName() {
    return name;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public String getScreen_name() {
    return screen_name;
  }

  public int getId() {
    return id;
  }

  public String getId_str() {
    return id_str;
  }
}
