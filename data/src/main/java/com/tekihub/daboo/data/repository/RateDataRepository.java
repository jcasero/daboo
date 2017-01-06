package com.tekihub.daboo.data.repository;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tekihub.daboo.domain.Rate;
import com.tekihub.daboo.domain.repository.RateRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class RateDataRepository implements RateRepository {
  private final String RATES_JSON = "rates.json";

  private final Context context;

  @Inject public RateDataRepository(Context context) {
    this.context = context;
  }

  @Override public Observable<List<Rate>> rates() {
    return Observable.defer(new Callable<ObservableSource<? extends List<Rate>>>() {

      @Override public ObservableSource<? extends List<Rate>> call() throws Exception {
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(RATES_JSON);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<ArrayList<Rate>>() {
        }.getType();
        List<Rate> rates = gson.fromJson(bufferString, listType);
        return Observable.fromArray(rates);
      }
    });
  }
}
