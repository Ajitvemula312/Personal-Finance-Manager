package com.example.jaccount.view;

import com.example.jaccount.AccountingApp;
import com.example.jaccount.Transaction;
import com.example.jaccount.control.IController;
import com.example.jaccount.view.viewcontrollers.SummaryViewController;
import com.example.jaccount.view.viewcontrollers.TransactionLayoutViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("transactions-view.fxml"));
    if (controlIn != null) {
      control = controlIn;
      fxmlLoader.setController(control);
    }
    try {
      scene = new Scene(fxmlLoader.load(), 1280, 720);
    } catch (IOException e) {
      e.printStackTrace();
    }
    setupTransactonList();
    setupSummaryView();
    stage.setTitle("JAccount");
    stage.setScene(scene);
    stage.show();
  }

  private void setupTransactonList() {
    VBox centerBox = getCenterBox();
    ListView<HBox> transactionsList = new ListView<>();
    transactionsList.setOrientation(Orientation.VERTICAL);
    transactionsList.getStyleClass().add("test");
    transactionsList.setItems(formatTransactions(control.getTransactions()));
    centerBox.setAlignment(Pos.BOTTOM_CENTER);
    centerBox.getChildren().add(transactionsList);
  }

  private void setupSummaryView() {
    HBox topbox = getTopBox();
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("summary-view.fxml"));
    fxmlLoader.setController(new SummaryViewController(Double.toString(control.getTotal())));

    try {
      topbox.getChildren().add(fxmlLoader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private VBox getCenterBox() {
    Node element = scene.lookup("#centerBox");
    if (element instanceof VBox) {
      return ((VBox) element);
    } else return null;
  }

  private HBox getTopBox() {
    Node element = scene.lookup("#topBox");
    if (element instanceof HBox) {
      return ((HBox) element);
    } else return null;
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
