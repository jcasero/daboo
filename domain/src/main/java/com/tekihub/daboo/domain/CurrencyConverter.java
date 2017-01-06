package com.tekihub.daboo.domain;

import com.tekihub.daboo.domain.entity.Rate;
import java.util.List;

public class CurrencyConverter {

  public double convert(List<Rate> rates, double quantity, String from, String to) {
    Graph graph = createGraphFromRates(rates);
    double conversionRate = graph.calculate(from, to);
    return conversionRate * quantity;
  }

  private Graph createGraphFromRates(List<Rate> rates) {
    Graph graph = new Graph();
    for (Rate rate : rates) {
      graph.addEdge(rate.getFrom(), rate.getTo(), rate.getRate());
    }
    return graph;
  }
}
