package com.example.jaccount.view;

import com.example.jaccount.AccountingApp;
import com.example.jaccount.Transaction;
import com.example.jaccount.control.IController;
import com.example.jaccount.view.viewcontrollers.MainTransactionsView;
import com.example.jaccount.view.viewcontrollers.TransactionItemView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AppView implements IAppView {
  private Stage stage = null;
  Scene scene = null;

  public AppView(Stage stageIn) {
    stage = stageIn;
  }

  public void initialize(IController controlIn) {
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("main-transactions-view.fxml"));
    fxmlLoader.setController(makeTransactionsViewController(controlIn.getTransactions(), controlIn.getTotal()));
    try {
      scene = new Scene(fxmlLoader.load(), 1280, 720);
    } catch (IOException e) {
      e.printStackTrace();
    }
    stage.setTitle("JAccount");
    stage.setScene(scene);
    stage.show();
  }

  private MainTransactionsView makeTransactionsViewController(ArrayList<Transaction> transactions, double total) {
    MainTransactionsView transactionsViewController = new MainTransactionsView();
    transactionsViewController.setListView(makeTransactionsList(transactions));
    transactionsViewController.setTotal(Double.toString(total));
    return transactionsViewController;
  }

  private ListView<HBox> makeTransactionsList(ArrayList<Transaction> transactions) {
    ListView<HBox> transactionsList = new ListView<>();
    transactionsList.setOrientation(Orientation.VERTICAL);
    transactionsList.getStyleClass().add("test");
    transactionsList.setItems(formatTransactions(transactions));
    return transactionsList;
  }


  private ObservableList<HBox> formatTransactions(ArrayList<Transaction> transactions) {
    ArrayList<HBox> boxArray = new ArrayList<>();

    for (Transaction trans : transactions) {
      FXMLLoader loader = new FXMLLoader(AccountingApp.class.getResource("transaction-layout.fxml"));
      HBox box = new HBox(20);
      TransactionItemView control = new TransactionItemView(
          trans.getName(),
          trans.getAmountString(),
          trans.getDate().toString()
      );

      loader.setController(control);

      try {
        box = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }

      boxArray.add(box);
    }
    return FXCollections.observableList(boxArray);
  }

}
