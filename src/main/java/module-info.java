module com.example.shopvaycuoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.shopvaycuoi to javafx.fxml;
    exports com.example.shopvaycuoi;
    exports com.example.shopvaycuoi.view;
    opens com.example.shopvaycuoi.view to javafx.fxml;
}