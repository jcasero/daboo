package com.tekihub.daboo.products.di;

import com.tekihub.daboo.di.ApplicationComponent;
import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.products.ProductsFragment;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ProductsModule.class)
public interface ProductsComponent {
  void inject(ProductsFragment productsFragment);
}
