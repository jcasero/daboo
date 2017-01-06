package com.tekihub.daboo.products.presenter;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.domain.interactor.GetProducts;
import com.tekihub.daboo.model.ProductModel;
import com.tekihub.daboo.mvp.BasePresenter;
import com.tekihub.daboo.products.adapter.ProductItem;
import com.tekihub.daboo.products.mapper.ProductsMapper;
import com.tekihub.daboo.products.mapper.ProductsParcelableMapper;
import io.reactivex.observers.DisposableObserver;
import java.util.List;
import javax.inject.Inject;

@PerActivity public class ProductsPresenter extends BasePresenter<ProductsPresenter.View> {
  private final GetProducts getProducts;
  private final ProductsMapper productsMapper;
  private final ProductsParcelableMapper productsParcelableMapper;
  private List<Product> products;

  @Inject public ProductsPresenter(GetProducts getProducts, ProductsMapper productsMapper,
      ProductsParcelableMapper productsParcelableMapper) {
    this.getProducts = getProducts;
    this.productsMapper = productsMapper;
    this.productsParcelableMapper = productsParcelableMapper;
  }

  public void fetchProducts() {
    view.showLoading();
    getProducts.execute(new DisposableObserver<List<Product>>() {

      @Override public void onNext(List<Product> value) {
        products = value;
      }

      @Override public void onError(Throwable e) {
        view.showNoProductsInfo();
      }

      @Override public void onComplete() {
        if (products == null || products.size() == 0) {
          view.showNoProductsInfo();
        } else {
          view.showProducts(productsMapper.transformAll(products));
        }
      }
    }, null);
  }

  public void onItemClicked(int position) {
    if (products != null && position < products.size()) {
      view.showProductDetail(productsParcelableMapper.transform(products.get(position)));
    }
  }

  public interface View extends BasePresenter.View {
    void showNoProductsInfo();

    void showProducts(List<ProductItem> items);

    void showLoading();

    void showProductDetail(ProductModel productModel);
  }
}
