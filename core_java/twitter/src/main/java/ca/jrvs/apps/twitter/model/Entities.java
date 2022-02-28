package ca.jrvs.apps.twitter.model;

import java.util.List;

public class Entities {
  private List<Hashtag> hashtagList;
  private List<UserMention> userMentionList;

  public void setHashtagList(List<Hashtag> hashtagList) {
    this.hashtagList = hashtagList;
  }

  public void setUserMentionList(List<UserMention> userMentionList) {
    this.userMentionList = userMentionList;
  }

  public List<Hashtag> getHashtagList() {
    return hashtagList;
  }

  public List<UserMention> getUserMentionList() {
    return userMentionList;
  }
}
