package com.example.jaccount.view;

import com.example.jaccount.AccountingApp;
import com.example.jaccount.controller.IController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView implements IAppView {
    private Stage stage = null;
    Scene scene = null;

    public AppView(Stage stageIn) throws IOException {
        stage = stageIn;
    }

    public void initialize(IController control){
        FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("start-view.fxml"));
        if (control != null) fxmlLoader.setController(control);
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("JAccount");
        stage.setScene(scene);
        stage.show();
    }

    public String getAmount(){
        Node element = scene.lookup("#amountField");;
        if (element instanceof TextField){
            return ((TextField) element).getCharacters().toString();
        } else return "";
    }
    public String getName(){
        Node element = scene.lookup("#nameField");;
        if (element instanceof TextField){
            return ((TextField) element).getCharacters().toString();
        } else return "";
    }
}
