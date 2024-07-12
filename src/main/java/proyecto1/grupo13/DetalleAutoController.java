/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Diego
 */
public class DetalleAutoController implements Initializable {
    public static ArrayListAuto<Auto> favoritos = App.crearArrayList("favoritos.txt");
    public static AnchorPane seleccionado;

    @FXML
    private Label lblDetalle;
    @FXML
    private HBox boxImages;
    @FXML
    private Button leftbtn;
    @FXML
    private Button rightbtn;
    @FXML
    private Button btnatras;

    private int currentImageIndex = 1; // índice de la imagen actual
    private Auto auto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnatras.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        auto = SeleccionaTuAutoController.autoSeleccionado;
        if (auto != null) {
            mostrarDetalle(auto);
            cargarImagen(auto, currentImageIndex);

            leftbtn.setOnAction(e -> cargarImagenAnterior());
            rightbtn.setOnAction(e -> cargarImagenSiguiente());
        }
    }

    public void mostrarDetalle(Auto auto) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(App.pathDescripciones + auto.getDescripcion()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblDetalle.setText(contenido.toString());
    }

    public void cargarImagen(Auto auto, int index) {
        boxImages.getChildren().clear(); // Limpiar imágenes existentes

        try {
            ImageView iv = new ImageView();
            iv.setFitHeight(250);
            iv.setFitWidth(400);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setPickOnBounds(true);
            iv.setPreserveRatio(true);

            Image image = new Image(new FileInputStream(App.pathCarros + auto.getMarca() + auto.getModelo() + auto.getColor() + "/" + index + ".png"));
            iv.setImage(image);
            boxImages.getChildren().add(iv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarImagenAnterior() {
        if (currentImageIndex > 1) {
            currentImageIndex--;
            cargarImagen(auto, currentImageIndex);
        }
    }

    private void cargarImagenSiguiente() {
        if (currentImageIndex < 3) { // Asumiendo que hay 3 imágenes
            currentImageIndex++;
            cargarImagen(auto, currentImageIndex);
        }
    }
}