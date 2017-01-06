package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.ConversionParams;
import com.tekihub.daboo.domain.CurrencyConverter;
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
