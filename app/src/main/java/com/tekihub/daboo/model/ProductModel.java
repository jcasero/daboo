package com.tekihub.daboo.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue public abstract class ProductModel implements Parcelable {
  public static ProductModel create(String sku, List<TransactionModel> transactions) {
    return new AutoValue_ProductModel(sku, transactions);
  }

  public abstract String sku();

  public abstract List<TransactionModel> transactions();
}
