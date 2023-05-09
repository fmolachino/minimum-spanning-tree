module com.example.mst3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.mst3 to javafx.fxml;
    exports com.example.mst3;
}