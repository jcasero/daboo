package com.tekihub.daboo.di;

import android.content.Context;
import com.tekihub.daboo.AndroidApplication;
import com.tekihub.daboo.UIThread;
import com.tekihub.daboo.data.repository.RateDataRepository;
import com.tekihub.daboo.data.repository.TransactionDataRepository;
import com.tekihub.daboo.data.repository.executor.JobExecutor;
import com.tekihub.daboo.domain.CurrencyConverter;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.RateRepository;
import com.tekihub.daboo.domain.repository.TransactionRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton RateRepository provideRateRepository(RateDataRepository rateDataRepository) {
    return rateDataRepository;
  }

  @Provides @Singleton TransactionRepository provideTransactionRepository(
      TransactionDataRepository transactionDataRepository) {
    return transactionDataRepository;
  }

  @Provides @Singleton CurrencyConverter provideCurrencyConverter() {
    return new CurrencyConverter();
  }
}
