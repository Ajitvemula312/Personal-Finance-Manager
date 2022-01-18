package com.example.jaccount.controller;

import com.example.jaccount.model.IModel;
import com.example.jaccount.view.IAppView;
import javafx.event.ActionEvent;

import java.time.LocalDate;

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

    public void onAddTransactionButtonClicked(ActionEvent actionEvent) {
        String amount = view.getAmount();
        String name = view.getName();
        model.addTransaction(name, Double.parseDouble(amount), LocalDate.now());
    }
}
