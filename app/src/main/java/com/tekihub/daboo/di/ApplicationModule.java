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
package com.tekihub.daboo.di;

import android.content.Context;
import com.tekihub.daboo.AndroidApplication;
import com.tekihub.daboo.UIThread;
import com.tekihub.daboo.data.repository.RateDataRepository;
import com.tekihub.daboo.data.repository.TransactionDataRepository;
import com.tekihub.daboo.data.repository.executor.JobExecutor;
import com.tekihub.daboo.domain.utils.CurrencyConverter;
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
