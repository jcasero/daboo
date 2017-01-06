package com.tekihub.daboo.products.mapper;

import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.products.AdapterItem;
import java.util.ArrayList;
import java.util.List;

public class ProductsMapper {

  public AdapterItem transform(Product product) {
    return new AdapterItem(product.getSku(), String.valueOf(product.getTransactions().size()));
  }

  public List<AdapterItem> transformAll(List<Product> products) {
    List<AdapterItem> adapterItems = new ArrayList<>();
    for (Product product : products) {
      adapterItems.add(transform(product));
    }
    return adapterItems;
  }
}
