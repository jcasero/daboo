package com.tekihub.daboo.products.mapper;

import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.model.ProductModel;
import com.tekihub.daboo.model.TransactionModel;
import java.util.List;
import javax.inject.Inject;

public class ProductsParcelableMapper {
  private TransactionParcelableMapper transactionParcelableMapper;

  @Inject public ProductsParcelableMapper(TransactionParcelableMapper transactionParcelableMapper) {
    this.transactionParcelableMapper = transactionParcelableMapper;
  }

  public ProductModel transform(Product product) {
    List<TransactionModel> transactionModels = transactionParcelableMapper.transformAll(product.getTransactions());
    return ProductModel.create(product.getSku(), transactionModels);
  }
}
