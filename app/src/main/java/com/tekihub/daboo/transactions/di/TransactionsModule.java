package com.tekihub.daboo.transactions.di;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.interactor.GetConversion;
import com.tekihub.daboo.transactions.mapper.ConversionMapper;
import com.tekihub.daboo.transactions.mapper.HeaderMapper;
import com.tekihub.daboo.transactions.presenter.TransactionsPresenter;
import dagger.Module;
import dagger.Provides;

@Module public class TransactionsModule {
  @Provides @PerActivity TransactionsPresenter providesTransactionsPresenter(GetConversion getConversion,
      ConversionMapper conversionMapper, HeaderMapper headerMapper) {
    return new TransactionsPresenter(getConversion, conversionMapper, headerMapper);
  }
}
