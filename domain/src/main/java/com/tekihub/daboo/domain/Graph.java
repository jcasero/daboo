package com.tekihub.daboo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<String, List<Edge>> nodes = new HashMap<>();

  public void addEdge(String from, String to, double value) {
    List<Edge> edge = getEdgesFromNode(from);
    edge.add(new Edge(to, value));
    nodes.put(from, edge);
  }

  public double calculate(String from, String to) {
    if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
      return 0;
    }
    return calculate(new ArrayList<String>(), from, to, 1);
  }

  private double calculate(List<String> visited, String from, String to, double unit) {
    if (from.equals(to)) {
      return unit;
    }

    visited.add(from);
    List<Edge> edges = nodes.get(from);
    for (Edge edge : edges) {
      if (!visited.contains(edge.getTo())) {
        double result = calculate(visited, edge.getTo(), to, edge.getValue()) * unit;
        if (result != 0) {
          return result;
        }
      }
    }

    return 0;
  }

  private List<Edge> getEdgesFromNode(String node) {
    List<Edge> edge;
    if (nodes.containsKey(node)) {
      edge = nodes.get(node);
    } else {
      edge = new ArrayList<>();
    }
    return edge;
  }
}
