package com.tekihub.daboo.di;

import android.content.Context;
import com.tekihub.daboo.MainApplication;
import com.tekihub.daboo.UIThread;
import com.tekihub.daboo.data.repository.RateDataRepository;
import com.tekihub.daboo.data.repository.executor.JobExecutor;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.RateRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final MainApplication application;

  public ApplicationModule(MainApplication application) {
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

  @Provides @Singleton RateRepository provideUserRepository(RateDataRepository rateDataRepository) {
    return rateDataRepository;
  }
}
