package com.example.jaccount.control;

import com.example.jaccount.Transaction;
import com.example.jaccount.model.IModel;
import com.example.jaccount.view.IAppView;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public interface IController {
    void setModel(IModel modelIn);
    void setView(IAppView viewIn);
    void addTransaction(ActionEvent actionEvent);
    void printTransactions(ActionEvent actionEvent);
    ArrayList<Transaction> getTransactions();
}
