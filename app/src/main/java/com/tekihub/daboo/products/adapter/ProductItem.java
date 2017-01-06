package com.tekihub.daboo.products.adapter;

public class ProductItem {
  private String title;
  private String subtitle;

  public ProductItem(String title, String subtitle) {
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
