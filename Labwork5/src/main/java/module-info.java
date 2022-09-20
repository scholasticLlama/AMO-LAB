module com.labs.labwork5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.labs.labwork5 to javafx.fxml;
    exports com.labs.labwork5;
}