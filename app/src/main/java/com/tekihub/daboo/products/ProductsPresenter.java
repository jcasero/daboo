package com.tekihub.daboo.products;

import com.tekihub.daboo.domain.entity.Product;
import com.tekihub.daboo.domain.interactor.GetProducts;
import com.tekihub.daboo.domain.interactor.UseCase;
import com.tekihub.daboo.mvp.BasePresenter;
import io.reactivex.observers.DisposableObserver;
import java.util.List;
import javax.inject.Named;

public class ProductsPresenter extends BasePresenter<ProductsPresenter.View> {
  private final UseCase getProducts;

  public ProductsPresenter(@Named(GetProducts.NAME) GetProducts getProducts) {
    this.getProducts = getProducts;
  }

  public void fetchProducts() {
    view.showLoading();
    getProducts.execute(new DisposableObserver<List<Product>>() {
      private List<Product> products;

      @Override public void onNext(List<Product> value) {
        products = value;
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onComplete() {
        if (products == null || products.size() == 0) {
          view.showNoProductsInfo();
        } else {
          view.showProducts(products);
        }
      }
    }, null);
  }

  interface View extends BasePresenter.View {
    void showNoProductsInfo();

    void showProducts(List<Product> products);

    void showLoading();
  }
}
