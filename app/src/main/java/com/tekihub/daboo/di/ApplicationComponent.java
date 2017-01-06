package com.tekihub.daboo.di;

import android.content.Context;
import com.tekihub.daboo.MainActivity;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.RateRepository;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(MainActivity mainActivity);

  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  RateRepository userRepository();
}
