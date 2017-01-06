package com.tekihub.daboo.domain;

public class Edge {
  private String to;
  private double value;

  public Edge(String to, double value) {
    this.to = to;
    this.value = value;
  }

  public String getTo() {
    return to;
  }

  public double getValue() {
    return value;
  }
}
