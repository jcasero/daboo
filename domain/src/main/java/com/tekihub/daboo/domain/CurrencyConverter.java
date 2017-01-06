package com.tekihub.daboo.domain;

import com.tekihub.daboo.domain.entity.Rate;
import java.util.List;
import javax.inject.Inject;

public class CurrencyConverter {
  private Graph graph;

  @Inject public CurrencyConverter() {
  }

  public double convert(List<Rate> rates, double quantity, String from, String to) {
    if (graph == null) {
      graph = createGraphFromRates(rates);
    }

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
