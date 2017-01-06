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
