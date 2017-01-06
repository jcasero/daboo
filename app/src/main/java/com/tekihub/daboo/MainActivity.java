package com.tekihub.daboo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tekihub.daboo.domain.interactor.GetTransactions;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
  @Inject GetTransactions getTransactions;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    inject();
  }

  private void inject() {
    ((MainApplication) getApplication()).getApplicationComponent().inject(this);
  }
}
