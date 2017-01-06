package com.tekihub.daboo.products.di;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.interactor.GetProducts;
import com.tekihub.daboo.products.ProductsPresenter;
import dagger.Module;
import dagger.Provides;

@Module public class ProductsModule {
  @Provides @PerActivity ProductsPresenter providesGetProductsUseCase(GetProducts getProducts) {
    return new ProductsPresenter(getProducts);
  }
}
