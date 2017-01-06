package com.tekihub.daboo.domain.entity;

public class Conversion {
  private Currency original;
  private Currency converted;

  public Conversion(Currency original) {
    this.original = original;
  }

  public Currency getOriginal() {
    return original;
  }

  public Currency getConverted() {
    return converted;
  }

  public void setConverted(Currency converted) {
    this.converted = converted;
  }
}
