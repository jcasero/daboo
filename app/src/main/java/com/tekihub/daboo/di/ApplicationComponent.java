package com.tekihub.daboo.di;

import android.content.Context;
import com.tekihub.daboo.domain.CurrencyConverter;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.RateRepository;
import com.tekihub.daboo.domain.repository.TransactionRepository;
import com.tekihub.daboo.products.ProductsActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(ProductsActivity productsActivity);

  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  RateRepository rateRepository();

  TransactionRepository transactionRepository();

  CurrencyConverter currencyConverter();
}
