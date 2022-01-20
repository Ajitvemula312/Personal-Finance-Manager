package com.example.jaccount.view;

import com.example.jaccount.AccountingApp;
import com.example.jaccount.Transaction;
import com.example.jaccount.control.IController;
import com.example.jaccount.view.viewcontrollers.TransactionLayoutViewController;
import com.example.jaccount.view.viewcontrollers.TransactionsViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AppView implements IAppView {
  private Stage stage = null;
  Scene scene = null;
  IController control;

  public AppView(Stage stageIn) throws IOException {
    stage = stageIn;
  }

  public void initialize(IController controlIn) {
    control = controlIn;
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("transactions-view.fxml"));
    TransactionsViewController transactionsViewController = new TransactionsViewController();
    setupTransactonList(transactionsViewController);
    transactionsViewController.setTotal(Double.toString(control.getTotal()));
    fxmlLoader.setController(transactionsViewController);
    try {
      scene = new Scene(fxmlLoader.load(), 1280, 720);
    } catch (IOException e) {
      e.printStackTrace();
    }

    stage.setTitle("JAccount");
    stage.setScene(scene);
    stage.show();
  }

  private void setupTransactonList(TransactionsViewController controllerIn) {
    ListView<HBox> transactionsList = new ListView<>();
    transactionsList.setOrientation(Orientation.VERTICAL);
    transactionsList.getStyleClass().add("test");
    transactionsList.setItems(formatTransactions(control.getTransactions()));
    controllerIn.setListView(transactionsList);

  }


  public String getAmount() {
    Node element = scene.lookup("#amountField");
    if (element instanceof TextField) {
      return ((TextField) element).getCharacters().toString();
    } else return "";
  }

  public String getName() {
    Node element = scene.lookup("#nameField");
    if (element instanceof TextField) {
      return ((TextField) element).getCharacters().toString();
    } else return "";
  }

  private ObservableList<HBox> formatTransactions(ArrayList<Transaction> transactions) {
    ArrayList<HBox> boxArray = new ArrayList<>();

    for (Transaction trans : transactions) {
      FXMLLoader loader = new FXMLLoader(AccountingApp.class.getResource("transaction-layout.fxml"));
      HBox box = new HBox(20);
      TransactionLayoutViewController control = new TransactionLayoutViewController(
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
