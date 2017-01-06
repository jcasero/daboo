package com.tekihub.daboo.transactions.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyUtils {
  public static String getValueWithCurrency(double value, String currency) {
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
    format.setCurrency(Currency.getInstance(currency));
    return format.format(value);
  }
}
