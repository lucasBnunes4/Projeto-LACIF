module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens main to javafx.fxml;
    exports main;



    opens main.models to javafx.base, javafx.fxml;
}