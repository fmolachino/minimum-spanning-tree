module com.example.mst2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mst2 to javafx.fxml;
    exports com.example.mst2;
}