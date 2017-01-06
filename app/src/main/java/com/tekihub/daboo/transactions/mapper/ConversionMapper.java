package com.tekihub.daboo.transactions.mapper;

import com.tekihub.daboo.domain.entity.Conversion;
import com.tekihub.daboo.transactions.adapter.ConversionItem;
import com.tekihub.daboo.transactions.adapter.TransactionItem;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import static com.tekihub.daboo.transactions.utils.CurrencyUtils.getValueWithCurrency;

public class ConversionMapper {

  @Inject public ConversionMapper() {

  }

  public TransactionItem transform(Conversion conversion) {
    return new ConversionItem(
        getValueWithCurrency(conversion.getConverted().getValue(), conversion.getConverted().getCurrency()),
        getValueWithCurrency(conversion.getOriginal().getValue(), conversion.getOriginal().getCurrency()));
  }

  public List<TransactionItem> transformAll(List<Conversion> conversions) {
    ArrayList<TransactionItem> transactionItems = new ArrayList<>();
    for (Conversion conversion : conversions) {
      transactionItems.add(transform(conversion));
    }
    return transactionItems;
  }
}
