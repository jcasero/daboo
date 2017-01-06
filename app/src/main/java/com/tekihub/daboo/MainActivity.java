package com.tekihub.daboo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    inject();
  }

  private void inject() {
    ((AndroidApplication) getApplication()).getApplicationComponent().inject(this);
  }
}
