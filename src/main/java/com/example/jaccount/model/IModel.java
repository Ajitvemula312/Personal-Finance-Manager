package com.example.jaccount.model;

import com.example.jaccount.Transaction;

import java.util.List;

public interface IModel {
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    double sumAllTransactions();
}
