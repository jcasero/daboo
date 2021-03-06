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
