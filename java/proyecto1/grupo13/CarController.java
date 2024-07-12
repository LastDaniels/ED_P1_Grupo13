/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarController implements Initializable {

    @FXML
    private Label lblMessage;
    @FXML
    private Label lblConfirma;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAction;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtKim;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ImageView imgCar;

    public static String[] selected;
    public static Auto car;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (selected != null) {
            car = new Auto(selected[0], selected[1], selected[2], selected[3], Integer.parseInt(selected[4]),
                    Double.parseDouble(selected[5]), Integer.parseInt(selected[6]), selected[7], selected[8]);
        }
        
        btnBack.setOnAction(e -> {
            if (Action("Confirmation Dialog", "¿Desea salir?")) {
                ExitWindow();
            }
        });
        
        if (selected == null) {
            try {
                Image img = new Image(new FileInputStream(App.pathImg + "auto.png"));
                imgCar.setImage(img);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            btnAction.setOnAction(e -> {
                if (Action("Confirmation Dialog", "¿Desea agregar este auto?")) {
                    String tipo = txtType.getText();
                    String marca = txtMarca.getText();
                    String modelo = txtModelo.getText();
                    String color = txtColor.getText();
                    String anio = txtYear.getText();
                    String kim = txtKim.getText();
                    String precio = txtPrice.getText();
                    String desc = txtDescription.getText();
                    String[] line = {tipo, marca, modelo, color, kim, precio, anio, "logo.jpeg", desc};

                    if (tipo.isEmpty() || marca.isEmpty() || modelo.isEmpty() || color.isEmpty() ||
                            kim.isEmpty() || precio.isEmpty() || anio.isEmpty() || desc.isEmpty()) {
                        lblConfirma.setText("Debes completar todos los campos.");
                    } else {
                        lblConfirma.setText("Ingreso con éxito");
                        Fichero.escribir(App.pathArchivos + "autos.txt", String.join(",", line));
                        Platform.runLater(() -> {
                            try {
                                Thread.sleep(1000); // Esperar 3 segundos
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            ExitWindow();
                        });
                    }
                }
            });
        } else {
            lblMessage.setText("Edita el auto");
            txtType.setText(selected[0]);
            txtMarca.setText(selected[1]);
            txtModelo.setText(selected[2]);
            txtColor.setText(selected[3]);
            txtKim.setText(String.valueOf(selected[4]));
            txtPrice.setText(String.valueOf(selected[5]));
            txtYear.setText(String.valueOf(selected[6]));
            txtDescription.setText(selected[8]);

            try {
                Image img = new Image(new FileInputStream(App.pathImagenes + selected[7]));
                imgCar.setImage(img);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            btnAction.setText("Editar");
            btnAction.setOnAction(e -> {
                if (Action("Confirmation Dialog", "¿Desea editar este auto?")) {
                    if (txtType.getText().isEmpty() || txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() ||
                            txtColor.getText().isEmpty() || txtKim.getText().isEmpty() || txtPrice.getText().isEmpty() ||
                            txtYear.getText().isEmpty() || txtDescription.getText().isEmpty()) {
                        lblConfirma.setText("Debes completar todos los campos.");
                    } else {
                        lblConfirma.setText("Actualización con éxito");
                        String line = "";
                        for (Auto a : App.crearArrayList("autos.txt")) {
                            if (!a.equals(car)) {
                                if (!line.isEmpty()) {
                                    line += "\n" + a.toWrite();
                                } else {
                                    line += a.toWrite();
                                }
                            }else{
                                Auto lineauto=new Auto(txtType.getText(),txtMarca.getText(),txtModelo.getText(),txtColor.getText(),Integer.parseInt(txtKim.getText()),
                                Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtYear.getText()),selected[7],txtDescription.getText());
                                if (!line.isEmpty()) {
                                    line += "\n" + lineauto.toWrite();
                                } else {
                                    line += lineauto.toWrite();
                                }
                            }
                        }
                        Fichero.sobreEscribir(App.pathArchivos + "autos.txt", line);
                        SeleccionaTuAutoController.seleccionado = null;
                        selected=null;
                        car=null;
                        
                        Platform.runLater(() -> {
                            try {
                                Thread.sleep(1000); 
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            ExitWindow();
                        });
                    }
                }
            });
        }
    }

    boolean Action(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);

        Optional<ButtonType> buttonSelected = alert.showAndWait();
        return buttonSelected.isPresent() && buttonSelected.get() == ButtonType.OK;
    }

    void ExitWindow() {
        try {
            App.setRoot("basedatos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

