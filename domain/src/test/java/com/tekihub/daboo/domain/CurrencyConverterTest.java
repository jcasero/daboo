package com.tekihub.daboo.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class) public class CurrencyConverterTest {
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