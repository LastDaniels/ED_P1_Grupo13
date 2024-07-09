/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;

import java.io.IOException;
import java.net.URL;


import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Saikotek
 */
public class BaseDatosController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView<Auto> tblCar;
    @FXML
    private TableColumn<Auto, String> idTipo;
    @FXML
    private TableColumn<Auto, String> idMarca;
    @FXML
    private TableColumn<Auto, String> idModelo;
    @FXML
    private TableColumn<Auto, String> idColor;
    @FXML
    private TableColumn<Auto, Integer> idKim;
    @FXML
    private TableColumn<Auto, Double> idPrecio;
    @FXML
    private TableColumn<Auto, Integer> idAnio;
    public static String[] autoSelected;
    public static ArrayListAuto<Auto> autos = App.crearArrayList("autos.txt");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.SetButtons();
        tblCar.getItems().clear();
        cargarAutos(App.crearArrayList("autos.txt"));

    }

    void SetButtons() {
        btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

        btnAdd.setOnMouseClicked(e -> {
            try {
                CarController.selected = null;
                App.setRoot("car");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

        btnEdit.setOnMouseClicked(e -> {
            try {
                Auto a = tblCar.getSelectionModel().getSelectedItem();
                String[] datosSeleccionados = {a.getTipo(), a.getMarca(), a.getModelo(), a.getColor(),
                    String.valueOf(a.getKilometraje()), String.valueOf(a.getPrecio()), String.valueOf(a.getAnio()),
                    a.getImagen(), a.getDescripcion()};
                CarController.selected = datosSeleccionados;
                App.setRoot("car");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

       btnDelete.setOnMouseClicked(e -> {
    Auto selected = tblCar.getSelectionModel().getSelectedItem();
    if (selected != null) {
        autos.removeElement(selected);

        StringBuilder lineas = new StringBuilder();

        for (int i = 0; i < autos.size(); i++) {
            String[] datosSeleccionados = {
                autos.get(i).getTipo(),
                autos.get(i).getMarca(),
                autos.get(i).getModelo(),
                autos.get(i).getColor(),
                String.valueOf(autos.get(i).getKilometraje()),
                String.valueOf(autos.get(i).getPrecio()),
                String.valueOf(autos.get(i).getAnio()),
                autos.get(i).getImagen(),
                autos.get(i).getDescripcion()
            };
            lineas.append(String.join(",", datosSeleccionados));
            if (i != autos.size() - 1) {
                lineas.append("\n");
            }
        }

        try {
            Fichero.sobreEscribir(App.pathArchivos + "autos.txt", lineas.toString());
        } catch (Exception ex) {
            System.err.println("Error al sobrescribir el archivo: " + ex.getMessage());
        }

        try {
            ArrayListAuto<Auto> nuevosAutos = App.crearArrayList("autos.txt");
            cargarAutos(nuevosAutos);
            tblCar.setItems(FXCollections.observableArrayList(nuevosAutos));
        } catch (Exception ex) {
            System.err.println("Error al cargar autos: " + ex.getMessage());
        }
    } else {
        System.out.println("No se ha seleccionado ningún auto para eliminar.");
    }
});

    }

    void cargarAutos(ArrayListAuto<Auto> autos) {

        ObservableList<Auto> list = FXCollections.observableArrayList(autos);
        idTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        idMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        idModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        idColor.setCellValueFactory(new PropertyValueFactory<>("Color"));
        idKim.setCellValueFactory(new PropertyValueFactory<>("Kilometraje"));
        idPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        idAnio.setCellValueFactory(new PropertyValueFactory<>("Anio"));

        tblCar.setItems(list);

    }

}
