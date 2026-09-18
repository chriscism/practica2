module com.example.practica2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica2 to javafx.fxml;
    exports com.example.practica2;
}