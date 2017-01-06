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
package com.tekihub.daboo.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class TransactionModel implements Parcelable {
  public static TransactionModel create(double amount, String sku, String currency) {
    return new AutoValue_TransactionModel(amount, sku, currency);
  }

  public abstract double amount();

  public abstract String sku();

  public abstract String currency();
}
