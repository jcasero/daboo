package com.tekihub.daboo.products.mapper;

import android.content.Context;
import com.tekihub.daboo.R;
import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.products.adapter.ProductItem;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ProductsMapper {
  private Context context;

  @Inject public ProductsMapper(Context context) {
    this.context = context;
  }

  public ProductItem transform(Product product) {
    int size = product.getTransactions().size();
    return new ProductItem(product.getSku(),
        context.getResources().getQuantityString(R.plurals.num_transactions, size, size));
  }

  public List<ProductItem> transformAll(List<Product> products) {
    List<ProductItem> productItems = new ArrayList<>();
    for (Product product : products) {
      productItems.add(transform(product));
    }
    return productItems;
  }
}
