package com.tekihub.daboo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.tekihub.daboo.domain.Rate;
import com.tekihub.daboo.domain.interactor.GetRates;
import io.reactivex.observers.DisposableObserver;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  @Inject GetRates getRates;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((MainApplication)getApplication()).getApplicationComponent().inject(this);
    getRates.execute(new DisposableObserver<List<Rate>>() {
      @Override public void onNext(List<Rate> value) {
        Log.d(TAG, "onNext: ");
      }

      @Override public void onError(Throwable e) {
        Log.d(TAG, "onError: ");
      }

      @Override public void onComplete() {
        Log.d(TAG, "onComplete: ");
      }
    }, null);
  }
}
