module com.example.fabric {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fabric to javafx.fxml;
    exports com.example.fabric;
}