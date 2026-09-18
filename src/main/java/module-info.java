module com.example.practica2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica2 to javafx.fxml;
    exports com.example.practica2;
    exports com.example.practica2.modelo;
    opens com.example.practica2.modelo to javafx.fxml;
    exports com.example.practica2.vista;
    opens com.example.practica2.vista to javafx.fxml;
    exports com.example.practica2.controlador;
    opens com.example.practica2.controlador to javafx.fxml;
}