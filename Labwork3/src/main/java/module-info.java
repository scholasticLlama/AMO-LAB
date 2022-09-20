module com.labs.labwork3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.labs.labwork3 to javafx.fxml;
    exports com.labs.labwork3;
}