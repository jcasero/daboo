package com.tekihub.daboo.domain.entity;

public class Transaction {
  private double amount;
  private String sku;
  private String currency;

  public Transaction(double amount, String sku, String currency) {
    this.amount = amount;
    this.sku = sku;
    this.currency = currency;
  }

  public double getAmount() {
    return amount;
  }

  public String getSku() {
    return sku;
  }

  public String getCurrency() {
    return currency;
  }
}
