package com.example.jaccount;

import java.sql.Date;

public class Transaction {
  private String name;
  private double amount;
  private Date date;
  private String category;

  public Transaction(String nameIn, double amountIn, Date dateIn) {
    name = nameIn;
    amount = amountIn;
    date = dateIn;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  public String getAmountString() {
    if (amount < 0)
      return String.format("-$" + "%.2f", -amount);
    else
      return String.format("$" + "%.2f", amount);
  }

  public Date getDate() {
    return date;
  }
}
