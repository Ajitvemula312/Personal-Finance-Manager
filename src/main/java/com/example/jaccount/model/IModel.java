package com.example.jaccount.model;

import com.example.jaccount.Transaction;

import java.util.List;

public interface IModel {
    public void addTransaction(Transaction transaction);
    public List<Transaction> getTransactions();
}
