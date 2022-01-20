package com.example.jaccount;

import com.example.jaccount.control.AppController;
import com.example.jaccount.model.Model;
import com.example.jaccount.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class AccountingApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppView view = new AppView(stage);
        Model model = new Model();
        AppController controller = new AppController();
        controller.setModel(model);
        controller.setView(view);
    }

    public static void main(String[] args) {
        launch();
    }
}