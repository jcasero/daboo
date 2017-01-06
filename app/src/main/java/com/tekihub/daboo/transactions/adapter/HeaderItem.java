package com.tekihub.daboo.transactions.adapter;

public class HeaderItem extends TransactionItem {
  private String title;

  public HeaderItem(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
