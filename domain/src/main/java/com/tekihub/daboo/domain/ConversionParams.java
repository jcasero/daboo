package com.tekihub.daboo.domain;

import com.tekihub.daboo.domain.entity.Conversion;
import java.util.List;

public class ConversionParams {
  private final List<Conversion> conversions;
  private final String to;

  public ConversionParams(List<Conversion> conversions, String to) {
    this.conversions = conversions;
    this.to = to;
  }

  public List<Conversion> getConversions() {
    return conversions;
  }

  public String getTo() {
    return to;
  }
}
