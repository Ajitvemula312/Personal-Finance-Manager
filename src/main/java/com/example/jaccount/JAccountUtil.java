package com.example.jaccount;

public class JAccountUtil {

  public static String formatAsMoney(double amountIn) {
    if (amountIn < 0)
      return String.format("-$" + "%.2f", -amountIn);
    else
      return String.format("$" + "%.2f", amountIn);
  }
}
