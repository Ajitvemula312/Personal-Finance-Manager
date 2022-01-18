package com.example.jaccount.model;

import java.time.LocalDate;

public interface IModel {
    public void addTransaction(String nameIn, double amountIn, LocalDate dateIn);
}
