package com.tekihub.daboo.products;

public class AdapterItem {
  private String title;
  private String subtitle;

  public AdapterItem(String title, String subtitle) {
    this.title = title;
    this.subtitle = subtitle;
  }

  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }
}
