/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class PrincipalController implements Initializable {

    @FXML
    private Button buttonAutos;
    @FXML
    private Button btnFavoritos;
    @FXML
    private Button buttonRegitros;
    @FXML
    private ImageView imagePrincipal;
    @FXML
    private Button btnsalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarImagenes();

        buttonAutos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("buscador");
                } catch (IOException ex) {
                }
            }
        });
        btnFavoritos.setOnMouseClicked((MouseEvent e) -> {
            try {
                
        System.out.println(FavoritosController.fav.length == 1);
                App.setRoot("favoritos");
            } catch (IOException ex) {
            }
        });

        buttonRegitros.setOnAction(e -> {
            try {
                App.setRoot("basedatos");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

    }

    public void cargarImagenes() {
        try {
           Image img = new Image(new FileInputStream(App.pathImg + "logo.jpeg"));
            imagePrincipal.setImage(img);
            imagePrincipal.setFitHeight(300);
            imagePrincipal.setFitWidth(200);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void salir(ActionEvent e){
        exit();
    }
}