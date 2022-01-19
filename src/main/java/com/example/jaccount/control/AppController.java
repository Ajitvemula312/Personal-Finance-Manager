package com.example.jaccount.control;

import com.example.jaccount.Transaction;
import com.example.jaccount.model.IModel;
import com.example.jaccount.view.IAppView;
import javafx.event.ActionEvent;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class AppController implements IController{
    private IModel model;
    private IAppView view;
    public AppController(){

    }

    @Override
    public void setModel(IModel modelIn) {
        model = modelIn;
    }

    @Override
    public void setView(IAppView viewIn) {
        view = viewIn;
        view.initialize(this);
    }

    public void addTransaction(ActionEvent actionEvent) {
        double amount = Double.parseDouble(view.getAmount());
        String name = view.getName();
        Date date = Date.valueOf(LocalDate.now());
        Transaction transaction = new Transaction(name, amount, date);
        model.addTransaction(transaction);
    }

    public ArrayList<Transaction> getTransactions(){
        return (ArrayList<Transaction>) model.getTransactions();
    }

    @Override
    public void printTransactions(ActionEvent actionEvent) {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) model.getTransactions();
        System.out.println(transactions.toString());
    }
}
