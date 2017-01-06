package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.ConversionParams;
import com.tekihub.daboo.domain.CurrencyConverter;
import com.tekihub.daboo.domain.Rate;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class GetConversion extends UseCase<Double, ConversionParams> {
  private final CurrencyConverter converter;
  private final GetRates getRates;

  @Inject GetConversion(CurrencyConverter converter, GetRates getRates, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.converter = converter;
    this.getRates = getRates;
  }

  @Override Observable<Double> buildUseCaseObservable(final ConversionParams conversionParams) {
    return Observable.defer(new Callable<ObservableSource<? extends Double>>() {
      @Override public ObservableSource<? extends Double> call() throws Exception {
        List<Rate> rates = getRates.buildUseCaseObservable(null).blockingFirst();
        return Observable.just(converter.convert(rates, conversionParams.getQuantity(), conversionParams.getFrom(),
            conversionParams.getTo()));
      }
    });
  }
}
