package com.example.jaccount.view.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SummaryView extends HBox implements Initializable {
  @FXML
  private Label totalLabel;
  @FXML
  private Label spendLabel;
  @FXML
  private Label saveLabel;
  @FXML
  private VBox labelColumn;
  @FXML
  private VBox amountColumn;
  private HashMap<String, String> categories;
  private String total;

  public SummaryView() {
  }

  public void setCategories(HashMap<String, String> categoriesIn){
    categories = categoriesIn;
  }

  public void setTotal(String textIn) {
    total = textIn;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    totalLabel.setText(total);
    for (String category : categories.keySet()){
      int addIndex = labelColumn.getChildren().indexOf(totalLabel)-1;
      Label label = new Label(category + ":");
      labelColumn.getChildren().add(1, label);
      Label amount = new Label(categories.get(category));
      amountColumn.getChildren().add(1, amount);
    }

  }
}
