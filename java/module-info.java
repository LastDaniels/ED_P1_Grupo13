module cecy.proyecto1grupo8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens proyecto1.grupo13 to javafx.fxml;
    exports proyecto1.grupo13;
}
