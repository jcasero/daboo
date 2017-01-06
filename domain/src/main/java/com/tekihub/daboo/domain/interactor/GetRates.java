package com.tekihub.daboo.domain.interactor;

import com.tekihub.daboo.domain.entity.Rate;
import com.tekihub.daboo.domain.executor.PostExecutionThread;
import com.tekihub.daboo.domain.executor.ThreadExecutor;
import com.tekihub.daboo.domain.repository.RateRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class GetRates extends UseCase<List<Rate>, Void> {
  private final RateRepository rateRepository;

  @Inject GetRates(RateRepository rateRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.rateRepository = rateRepository;
  }

  @Override Observable<List<Rate>> buildUseCaseObservable(Void aVoid) {
    return rateRepository.rates();
  }
}
