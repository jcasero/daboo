package com.tekihub.daboo.transactions.adapter;

public class ConversionItem extends TransactionItem {
  private String title;
  private String subtitle;

  public ConversionItem(String title, String subtitle) {
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
