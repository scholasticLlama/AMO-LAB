module com.labs.labwork4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.labs.labwork4 to javafx.fxml;
    exports com.labs.labwork4;
}