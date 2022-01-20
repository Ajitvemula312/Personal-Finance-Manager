package com.example.jaccount;

import com.example.jaccount.view.viewcontrollers.TransactionItemView;

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
    if (amount < 0)
      return String.format("-$" + "%.2f", -amount);
    else
      return String.format("$" + "%.2f", amount);
  }

  public Date getDate() {
    return date;
  }

  public TransactionItemView getItemViewController(){
    TransactionItemView transactionView = new TransactionItemView(
        name,
        getAmountString(),
        date.toString(),
        category
    );
    return transactionView;
  }

  public String getCategory() {
    return category;
  }
}
