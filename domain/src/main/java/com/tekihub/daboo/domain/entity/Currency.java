package com.tekihub.daboo.domain.entity;

public class Currency {
  private String currency;
  private double value;

  public Currency(String currency, double value) {
    this.currency = currency;
    this.value = value;
  }

  public String getCurrency() {
    return currency;
  }

  public double getValue() {
    return value;
  }
}
