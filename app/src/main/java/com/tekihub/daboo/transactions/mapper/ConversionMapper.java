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
