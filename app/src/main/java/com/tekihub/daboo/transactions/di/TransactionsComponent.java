package com.tekihub.daboo.transactions.di;

import com.tekihub.daboo.di.ApplicationComponent;
import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.transactions.fragment.TransactionsFragment;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = TransactionsModule.class)
public interface TransactionsComponent {
  void inject(TransactionsFragment transactionsFragment);
}
