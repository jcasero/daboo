/**
 * Copyright (C) 2017 Jose Casero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tekihub.daboo.domain.util;

import com.tekihub.daboo.domain.entity.Graph;
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
