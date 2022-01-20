package com.example.jaccount.view.viewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryView extends HBox implements Initializable {
  @FXML
  private Label totalLabel;
  private String total;

  public SummaryView(String totalIn){
    total = totalIn;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    totalLabel.setText(total);
  }
}
