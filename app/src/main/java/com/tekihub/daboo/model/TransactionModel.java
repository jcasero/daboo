package com.tekihub.daboo.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class TransactionModel implements Parcelable {
  public static TransactionModel create(double amount, String sku, String currency) {
    return new AutoValue_TransactionModel(amount, sku, currency);
  }

  public abstract double amount();

  public abstract String sku();

  public abstract String currency();
}
