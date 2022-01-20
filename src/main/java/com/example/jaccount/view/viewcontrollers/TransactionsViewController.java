package com.example.jaccount.view.viewcontrollers;

import com.example.jaccount.AccountingApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsViewController implements Initializable {
  @FXML
  private HBox topBox;
  @FXML
  private VBox centerBox;
  private ListView<HBox> transactionsList;
  private String totalAmount;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (transactionsList != null) centerBox.getChildren().add(transactionsList);
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("summary-view.fxml"));
    fxmlLoader.setController(new SummaryViewController(totalAmount));

    try {
      topBox.getChildren().add(fxmlLoader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setListView(ListView<HBox> listIn) {
    transactionsList = listIn;
  }
  public void setTotal(String totalIn){
    totalAmount = totalIn;
  }
}
