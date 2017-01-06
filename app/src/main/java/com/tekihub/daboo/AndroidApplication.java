package com.tekihub.daboo;

import android.app.Application;
import com.tekihub.daboo.di.ApplicationComponent;
import com.tekihub.daboo.di.ApplicationModule;
import com.tekihub.daboo.di.DaggerApplicationComponent;

public class AndroidApplication extends Application {
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  private void initializeInjector() {
    applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
