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

  public Transaction(String nameIn, double amountIn, Date dateIn, String categoryIn) {
    name = nameIn;
    amount = amountIn;
    date = dateIn;
    category = categoryIn;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  public String getAmountString() {
    return JAccountUtil.formatAsMoney(amount);
  }

  public Date getDate() {
    return date;
  }

  public String getCategory() {
    return category;
  }
}
