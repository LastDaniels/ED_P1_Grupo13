package proyecto1.grupo13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class SeleccionaTuAutoController implements Initializable {

    @FXML
    private Button btnatras;
    @FXML
    private HBox hb;
    @FXML
    private Label lbltitulo;

    public static String[] datosBusqueda;
    public static ArrayListAuto<Auto> favoritos = App.crearArrayList("favoritos.txt");
    public static AnchorPane seleccionado;
    public static Auto autoSeleccionado;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LlenarDatos(hb, App.crearArrayList("autos.txt"), datosBusqueda);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void atras(ActionEvent event) {
        try {
            App.setRoot("buscador");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public static void mostrar(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        Iterator<Auto> it = (App.crearArrayList("autos.txt")).iterator();
        while(it.hasNext()) {
            Auto a = it.next();
            if (CompararAutoSeleccionado(a, source)) {
                seleccionado = source;
                autoSeleccionado = a; // Guardar el auto seleccionado
                String[] seleccionados = {a.getTipo(),a.getMarca(),a.getModelo(),a.getColor(),String.valueOf(a.getKilometraje()),String.valueOf(a.getPrecio()),String.valueOf(a.getAnio()),a.getImagen(),a.getDescripcion()};
                datosBusqueda = seleccionados;
                try {
                    App.setRoot("DetalleAuto");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void LlenarDatos(HBox hbox, ArrayListAuto<Auto> ar, String[] datos) throws FileNotFoundException {
        VBox vb = new VBox();
        vb.setPrefWidth(100);
        vb.setPrefHeight(200);
        vb.setSpacing(15);
        ArrayListAuto<Auto> autosBusqueda = new ArrayListAuto<>();
        if (datos.length == 1) {
            Iterator<Auto> it = ar.iterator();
            while(it.hasNext()){
                Auto a = it.next();
                autosBusqueda.add(a);
            }
        } else {
            Iterator<Auto> it = ar.iterator();
            while(it.hasNext()) {
                Auto a = it.next();
                if (datos[0].equals(a.getMarca())
                        && datos[1].equals(a.getModelo())
                        && Integer.parseInt(datos[2]) <= a.getKilometraje()
                        && Integer.parseInt(datos[3]) >= a.getKilometraje()
                        && Double.parseDouble(datos[4]) <= a.getPrecio()
                        && Double.parseDouble(datos[5]) >= a.getPrecio()) {
                    autosBusqueda.add(a);
                }
            }
        }

        for (int i = 0; i < autosBusqueda.size(); i++) {
            if (vb.getChildren().size() < 2) {
                LlenarVbox(vb, autosBusqueda.get(i));
            } else {
                hbox.getChildren().add(vb);
                vb = new VBox();
                vb.setPrefWidth(100);
                vb.setPrefHeight(200);
                vb.setSpacing(15);
                LlenarVbox(vb, autosBusqueda.get(i));
            }
        }

        if (!vb.getChildren().isEmpty()) {
            hbox.getChildren().add(vb);
        }
    }

    public static void LlenarVbox(VBox vb, Auto a) throws FileNotFoundException {
        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(100, 250);
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

        Image image = new Image(new FileInputStream(App.pathImagenes + a.getImagen()));
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

        Label lblprecio = new Label("$ " + String.valueOf(a.getPrecio()));
        lblprecio.setLayoutX(5);
        lblprecio.setLayoutY(210);
        lblprecio.setFont(new Font("System Bold", 16));

        ap.getChildren().addAll(iv, lblmodelo, lblanio, lblmarca, lbltipo, lblprecio);
        ap.setOnMouseClicked(e -> mostrar(e));

        vb.getChildren().add(ap);
    }

    public static boolean CompararAutoSeleccionado(Auto a, AnchorPane anchorPane) {
        Label lblmodelo = (Label) anchorPane.getChildren().get(1);
        Label lblanio = (Label) anchorPane.getChildren().get(2);
        Label lblmarca = (Label) anchorPane.getChildren().get(3);
        Label lbltipo = (Label) anchorPane.getChildren().get(4);
        Label lblprecio = (Label) anchorPane.getChildren().get(5);

        return a.getModelo().equals(lblmodelo.getText())
                && a.getAnio() == Integer.parseInt(lblanio.getText())
                && a.getMarca().equals(lblmarca.getText())
                && a.getTipo().equals(lbltipo.getText())
                && a.getPrecio() == Double.parseDouble(lblprecio.getText().substring(2));
    }

    @FXML
    public void marcarFavorito(ActionEvent event) {
        if (seleccionado == null) {
            lbltitulo.setText("Debes seleccionar un auto.");
        } else {
            Stage stage = new Stage();
            AnchorPane rootmensaje = new AnchorPane();
            VBox vbh = new VBox();
            vbh.setSpacing(60);
            vbh.setPadding(new Insets(50, 20, 0, 50));
            Label lbl = new Label();
            lbl.setText("¿Está seguro de añadir a favoritos?");
            lbl.setAlignment(Pos.CENTER);
            lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            lbl.setTextFill(Color.web("#205d65"));
            HBox hb = new HBox();
            hb.setPrefSize(150, 200);
            hb.setSpacing(125);
            Button btnaceptar = new Button();
            btnaceptar.setPrefSize(100, 60);
            btnaceptar.setStyle("-fx-background-color: #ffffff; -fx-border-color: #14a1b5;");
            btnaceptar.setText("Aceptar");
            btnaceptar.setTextFill(Color.web("#14a1b5"));
            btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            Button btncancelar = new Button();
            btncancelar.setPrefSize(100, 60);
            btncancelar.setStyle("-fx-background-color: #ffffff; -fx-border-color: #14a1b5;");
            btncancelar.setText("Cancelar");
            btncancelar.setTextFill(Color.web("#14a1b5"));
            btncancelar.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            Scene scene = new Scene(rootmensaje, 450, 240);
            rootmensaje.getChildren().add(vbh);
            vbh.getChildren().add(lbl);
            vbh.getChildren().add(hb);
            hb.getChildren().add(btnaceptar);
            hb.getChildren().add(btncancelar);
            vbh.setMargin(hb, new Insets(0, 10, 0, 10));
            stage.setScene(scene);
            stage.show();
            btnaceptar.setOnAction(e -> {
                stage.close();
                Iterator<Auto> it = (App.crearArrayList("autos.txt")).iterator();
                while(it.hasNext()) {
                    Auto a = it.next();
                    if (CompararAutoSeleccionado(a, seleccionado) && !favoritos.contiene(a)) {
                        Fichero.escribir(App.pathArchivos + "favoritos.txt", a.toWrite());
                    }
                }
            });
            btncancelar.setOnAction(e -> stage.close());
        }
    }
}