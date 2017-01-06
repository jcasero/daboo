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
package com.tekihub.daboo.domain;

import com.tekihub.daboo.domain.entity.Rate;
import com.tekihub.daboo.domain.utils.CurrencyConverter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class) public class CurrencyConverterTest {
  private List<Rate> rates;
  private CurrencyConverter currencyConverter = new CurrencyConverter();

  @Before public void setUp() {
    rates = givenAratesList();
  }

  @Test public void testShouldConvertFromUsdToAud() {
    double result = currencyConverter.convert(rates, 1, "USD", "AUD");
    assertEquals(0.6391, result, 0.0001);
  }

  @Test public void testShouldConvertFromUsdToCad() {
    double result = currencyConverter.convert(rates, 1, "USD", "CAD");
    assertEquals(1.09, result, 0.0001);
  }

  @Test public void testShouldConvertFromAudToUsd() {
    double result = currencyConverter.convert(rates, 1, "AUD", "USD");
    assertEquals(1.56, result, 0.0001);
  }

  @Test public void testShouldConvertFromAudToCad() {
    double result = currencyConverter.convert(rates, 1, "AUD", "CAD");
    assertEquals(1.7004, result, 0.0001);
  }

  @Test public void testShouldReturnNoConversion() {
    double result = currencyConverter.convert(rates, 1, "EUR", "CAD");
    assertEquals(0, result, 0);
  }

  private List<Rate> givenAratesList() {
    List<Rate> rates = new ArrayList<>();
    rates.add(new Rate("USD", "GBP", 0.77));
    rates.add(new Rate("GBP", "USD", 1.3));
    rates.add(new Rate("USD", "CAD", 1.09));
    rates.add(new Rate("CAD", "USD", 0.92));
    rates.add(new Rate("GBP", "AUD", 0.83));
    rates.add(new Rate("AUD", "GBP", 1.2));
    return rates;
  }
}