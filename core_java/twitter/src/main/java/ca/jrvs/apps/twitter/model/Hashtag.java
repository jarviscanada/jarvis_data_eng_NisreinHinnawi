package ca.jrvs.apps.twitter.model;

import java.util.List;

public class Hashtag {
  private String text;
  private List<Integer> indices;

  public void setText(String text) {
    this.text = text;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  public String getText() {
    return text;
  }

  public List<Integer> getIndices() {
    return indices;
  }
}
