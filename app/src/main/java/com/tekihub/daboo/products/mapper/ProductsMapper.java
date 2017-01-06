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
