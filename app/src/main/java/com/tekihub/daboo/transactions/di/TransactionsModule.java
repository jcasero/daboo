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
package com.tekihub.daboo.transactions.di;

import com.tekihub.daboo.di.PerActivity;
import com.tekihub.daboo.domain.interactor.GetConversion;
import com.tekihub.daboo.transactions.mapper.ConversionMapper;
import com.tekihub.daboo.transactions.mapper.HeaderMapper;
import com.tekihub.daboo.transactions.presenter.TransactionsPresenter;
import dagger.Module;
import dagger.Provides;

@Module public class TransactionsModule {
  @Provides @PerActivity TransactionsPresenter providesTransactionsPresenter(GetConversion getConversion,
      ConversionMapper conversionMapper, HeaderMapper headerMapper) {
    return new TransactionsPresenter(getConversion, conversionMapper, headerMapper);
  }
}
