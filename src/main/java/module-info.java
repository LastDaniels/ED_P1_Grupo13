module cecy.proyecto1grupo8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens proyecto1Grupo13 to javafx.fxml;
    exports proyecto1Grupo13;
}
