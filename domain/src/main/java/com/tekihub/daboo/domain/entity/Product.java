package com.tekihub.daboo.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {
  private String sku;
  private List<Transaction> transactions = new ArrayList<>();

  public Product(String sku) {
    this.sku = sku;
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public String getSku() {
    return sku;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }
}
