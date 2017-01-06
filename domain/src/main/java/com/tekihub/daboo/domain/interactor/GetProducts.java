package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.domain.entity.Transaction;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class GetProducts extends UseCase<List<Product>, Void> {
  private final GetTransactions getTransactions;
  private HashMap<String, Product> products = new HashMap<>();

  @Inject GetProducts(GetTransactions getTransactions, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.getTransactions = getTransactions;
  }

  @Override Observable<List<Product>> buildUseCaseObservable(Void aVoid) {
    return Observable.defer(new Callable<ObservableSource<? extends List<Product>>>() {
      @Override public ObservableSource<? extends List<Product>> call() throws Exception {
        List<Product> products = new ArrayList<>();
        List<Transaction> transactions = getTransactions.buildUseCaseObservable(null).blockingFirst();
        for (Transaction transaction : transactions) {
          processTransaction(products, transaction);
        }
        return Observable.fromArray(products);
      }
    });
  }

  private void processTransaction(List<Product> productList, Transaction transaction) {
    if (transaction == null || transaction.getSku() == null) {
      return;
    }

    Product product;
    String sku = transaction.getSku().toUpperCase();

    if (products.containsKey(sku)) {
      product = products.get(sku);
    } else {
      product = new Product(sku);
      productList.add(product);
      products.put(sku, product);
    }

    product.addTransaction(transaction);
  }
}
