module com.example.accountingprogram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.jaccount to javafx.fxml;
    exports com.example.jaccount;
    exports com.example.jaccount.view;
    opens com.example.jaccount.view to javafx.fxml;
    exports com.example.jaccount.controller;
    opens com.example.jaccount.controller to javafx.fxml;
}