package com.tekihub.daboo.products.di;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.interactor.GetProducts;
import com.tekihub.daboo.products.mapper.ProductsMapper;
import com.tekihub.daboo.products.mapper.ProductsParcelableMapper;
import com.tekihub.daboo.products.presenter.ProductsPresenter;
import dagger.Module;
import dagger.Provides;

@Module public class ProductsModule {
  @Provides @PerActivity ProductsPresenter providesGetProductsUseCase(GetProducts getProducts,
      ProductsMapper productsMapper, ProductsParcelableMapper productsParcelableMapper) {
    return new ProductsPresenter(getProducts, productsMapper, productsParcelableMapper);
  }
}
