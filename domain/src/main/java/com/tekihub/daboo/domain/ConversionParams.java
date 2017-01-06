package com.tekihub.daboo.domain;

public class ConversionParams {
  private final double quantity;
  private final String from;
  private final String to;

  public ConversionParams(double quantity, String from, String to) {
    this.quantity = quantity;
    this.from = from;
    this.to = to;
  }

  public double getQuantity() {
    return quantity;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }
}
