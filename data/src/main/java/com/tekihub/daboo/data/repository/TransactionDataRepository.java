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
package com.tekihub.daboo.data.repository;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tekihub.daboo.domain.entity.Transaction;
import com.tekihub.daboo.domain.repository.TransactionRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class TransactionDataRepository implements TransactionRepository {
  private static final String TRANSACTION_JSON = "transactions.json";
  private final Context context;

  @Inject TransactionDataRepository(Context context) {
    this.context = context;
  }

  @Override public Observable<List<Transaction>> transactions() {
    return Observable.defer(new Callable<ObservableSource<List<Transaction>>>() {
      @Override public ObservableSource<List<Transaction>> call() throws Exception {
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(TRANSACTION_JSON);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<ArrayList<Transaction>>() {
        }.getType();
        List<Transaction> transactions = gson.fromJson(bufferString, listType);
        return Observable.fromArray(transactions);
      }
    });
  }
}
