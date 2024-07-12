/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1.grupo13;

import static proyecto1.grupo13.SeleccionaTuAutoController.CompararAutoSeleccionado;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class FavoritosController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnRegresar;
    @FXML
    private HBox contenedor;
    @FXML
    private Button btnFavorito;

    public static String[] fav = { "Favorito" };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        try {
            SeleccionaTuAutoController.LlenarDatos(contenedor,App.crearArrayList("favoritos.txt"), fav);
        } catch (FileNotFoundException ex) {
        }
        btnRegresar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                }
            }
        });
    }
    
    @FXML
    public void quitarFavorito(ActionEvent event) {
        Stage stage = new Stage();
        AnchorPane rootmensaje=new AnchorPane();
        VBox vbh=new VBox();
        vbh.setSpacing(60);
        vbh.setPadding(new Insets(100, 20, 0, 20));
        Label lbl=new Label();
        lbl.setText("¿Está seguro de eliminar este auto de favoritos?");
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lbl.setTextFill(Color.web("#b74113"));
        HBox hb=new HBox();
        hb.setPrefSize(150,200);
        hb.setSpacing(150);
        Button btnaceptar=new Button();
        btnaceptar.setPrefSize(100, 40);
        btnaceptar.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b74113;");
        btnaceptar.setText("Aceptar");
        btnaceptar.setTextFill(Color.web("#faa632"));
        btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Button btncancelarm=new Button();
        btncancelarm.setPrefSize(100, 40);
        btncancelarm.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b74113;");
        btncancelarm.setText("Cancelar");
        btncancelarm.setTextFill(Color.web("#faa632"));
        btncancelarm.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Scene scene = new Scene(rootmensaje,475,300);
        rootmensaje.getChildren().add(vbh);
        vbh.getChildren().add(lbl);
        vbh.getChildren().add(hb);
        hb.getChildren().add(btnaceptar);
        hb.getChildren().add(btncancelarm);
        vbh.setMargin(hb,new Insets(0,50,0,50));
        stage.setScene(scene);
        stage.show();
        btnaceptar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                stage.close();
                Stage s=(Stage)((Button) event.getSource()).getScene().getWindow();
                s.close();
                String line="";
                for(Auto a:App.crearArrayList("favoritos.txt")){
                    if(!CompararAutoSeleccionado(a,SeleccionaTuAutoController.seleccionado)){
                        if(!line.equals("")) line+=("\n"+a.toWrite());
                        else line+=(a.toWrite());
                    }
                }
                Fichero.sobreEscribir(App.pathArchivos+"favoritos.txt",line);
                SeleccionaTuAutoController.seleccionado=null;
                try {
                    contenedor.getChildren().clear();
                    SeleccionaTuAutoController.LlenarDatos(contenedor,App.crearArrayList("favoritos.txt"), fav);
                } catch (FileNotFoundException ex) {
                }
            }
        });
        btncancelarm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                stage.close();
            }
        });
    }
    
}
