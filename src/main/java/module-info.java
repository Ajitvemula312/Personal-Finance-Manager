module com.example.accountingprogram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.jaccount to javafx.fxml;
    exports com.example.jaccount;
    exports com.example.jaccount.view;
    opens com.example.jaccount.view to javafx.fxml;
    opens com.example.jaccount.view.viewcontrollers to javafx.fxml;
    exports com.example.jaccount.control;
    opens com.example.jaccount.control to javafx.fxml;
}