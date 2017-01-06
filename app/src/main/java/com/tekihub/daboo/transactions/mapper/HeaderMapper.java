package com.tekihub.daboo.transactions.mapper;

import com.tekihub.daboo.transactions.adapter.HeaderItem;
import com.tekihub.daboo.transactions.utils.CurrencyUtils;
import javax.inject.Inject;

public class HeaderMapper {

  @Inject public HeaderMapper() {

  }

  public HeaderItem transform(double value, String currency) {
    return new HeaderItem(CurrencyUtils.getValueWithCurrency(value, currency));
  }
}
