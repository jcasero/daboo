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
