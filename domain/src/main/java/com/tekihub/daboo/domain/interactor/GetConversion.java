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
package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.entity.ConversionParams;
import com.tekihub.daboo.domain.utils.CurrencyConverter;
import com.tekihub.daboo.domain.entity.Conversion;
import com.tekihub.daboo.domain.entity.Currency;
import com.tekihub.daboo.domain.entity.Rate;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class GetConversion extends UseCase<List<Conversion>, ConversionParams> {
  private final CurrencyConverter converter;
  private final GetRates getRates;

  @Inject GetConversion(CurrencyConverter converter, GetRates getRates, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.converter = converter;
    this.getRates = getRates;
  }

  @Override Observable<List<Conversion>> buildUseCaseObservable(final ConversionParams params) {
    return Observable.defer(new Callable<ObservableSource<? extends List<Conversion>>>() {
      @Override public ObservableSource<? extends List<Conversion>> call() throws Exception {
        List<Conversion> converted = new ArrayList<>();
        List<Rate> rates = getRates.buildUseCaseObservable(null).blockingFirst();
        List<Conversion> conversions = params.getConversions();
        for (Conversion conversion : conversions) {
          double value =
              converter.convert(rates, conversion.getOriginal().getValue(), conversion.getOriginal().getCurrency(),
                  params.getTo());
          Conversion newConversion = new Conversion(conversion.getOriginal());
          newConversion.setConverted(new Currency(params.getTo(), value));
          converted.add(newConversion);
        }
        return Observable.just(converted);
      }
    });
  }
}
