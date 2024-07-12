/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class TodosLosCarrosController implements Initializable {

    @FXML
    private HBox boxImages;
    @FXML
    private Button leftbtn;
    @FXML
    private Button rightbtn;
    @FXML
    private Button btnAtras;

    private ArrayListAuto<Auto> autos;
    private int currentIndex = 0;
    private static final int ITEMS_PER_PAGE = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            autos = App.crearArrayList("autos.txt");
            if (autos != null && !autos.isEmpty()) {
                mostrarAutos();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void volver(ActionEvent event) {
        try {
            App.setRoot("principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void anterior(ActionEvent event) {
        if (currentIndex >= ITEMS_PER_PAGE) {
            currentIndex -= ITEMS_PER_PAGE;
            try {
                mostrarAutos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void siguiente(ActionEvent event) {
        if (currentIndex + ITEMS_PER_PAGE < autos.size()) {
            currentIndex += ITEMS_PER_PAGE;
            try {
                mostrarAutos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mostrarAutos() {
        boxImages.getChildren().clear();
        HBox hb = new HBox();
        hb.setPrefWidth(400);
        hb.setPrefHeight(200);
        hb.setSpacing(15);

        int endIndex = Math.min(currentIndex + ITEMS_PER_PAGE, autos.size());
        for (int i = currentIndex; i < endIndex; i++) {
            llenarHbox(hb, autos.get(i));
        }

        boxImages.getChildren().add(hb);
    }

    public void llenarHbox(HBox hb, Auto a) {
        try {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(200, 250);
            ap.setStyle("-fx-border-color: #808080; -fx-border-width: 2;");

            ImageView iv = new ImageView();
            iv.setLayoutX(5);
            iv.setLayoutY(5);
            iv.setFitHeight(100);
            iv.setFitWidth(150);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setPickOnBounds(true);
            iv.setPreserveRatio(false);

            String imagePath = App.pathImagenes + a.getImagen();
            Image image = new Image(new FileInputStream(imagePath));
            iv.setImage(image);

            Label lblmodelo = new Label(a.getModelo());
            lblmodelo.setLayoutX(5);
            lblmodelo.setLayoutY(155);
            lblmodelo.setFont(new Font("System Bold", 16));

            Label lblanio = new Label(String.valueOf(a.getAnio()));
            lblanio.setLayoutX(100);
            lblanio.setLayoutY(159);
            lblanio.setStyle("-fx-border-color: #808080;");

            Label lblmarca = new Label(a.getMarca());
            lblmarca.setLayoutX(5);
            lblmarca.setLayoutY(175);

            Label lbltipo = new Label(a.getTipo());
            lbltipo.setLayoutX(5);
            lbltipo.setLayoutY(190);

            Label lblprecio = new Label("$ " + a.getPrecio());
            lblprecio.setLayoutX(5);
            lblprecio.setLayoutY(210);
            lblprecio.setFont(new Font("System Bold", 16));
            
            ap.getChildren().addAll(iv, lblmodelo, lblanio, lblmarca, lbltipo, lblprecio);
            ap.setOnMouseClicked((MouseEvent event) -> {
                try {
                    SeleccionaTuAutoController.autoSeleccionado = a;
                    App.setRoot("DetalleAuto");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            hb.getChildren().add(ap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}