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
package com.tekihub.daboo.products.mapper;

import com.tekihub.daboo.domain.entity.Transaction;
import com.tekihub.daboo.model.TransactionModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class TransactionParcelableMapper {

  @Inject public TransactionParcelableMapper() {

  }

  public TransactionModel transform(Transaction transaction) {
    return TransactionModel.create(transaction.getAmount(), transaction.getSku(), transaction.getCurrency());
  }

  public List<TransactionModel> transformAll(List<Transaction> transactions) {
    List<TransactionModel> transactionModels = new ArrayList<>();
    for (Transaction transaction : transactions) {
      transactionModels.add(transform(transaction));
    }
    return transactionModels;
  }
}
