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
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainView implements Initializable {
  @FXML
  private HBox topBox;
  @FXML
  private VBox centerBox;
  private ListView<HBox> transactionsList;
  private String totalAmount;
  private String save;
  private String spend;
  private HashMap<String,String> categoryAmounts;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (transactionsList != null) centerBox.getChildren().add(transactionsList);
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("summary-view.fxml"));
    SummaryView summary = new SummaryView();
    summary.setTotal(totalAmount);
    summary.setCategories(categoryAmounts);
    fxmlLoader.setController(summary);

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
  public void setSave(String textIn){
    save = textIn;
  }
  public void setSpend(String textIn){
    spend = textIn;
  }

  public void setCategories(HashMap<String, String> categoriesandAmounts) {
    categoryAmounts = categoriesandAmounts;
  }
}
