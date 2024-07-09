/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto1.grupo13;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.*;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class BuscadorController implements Initializable {

    @FXML
    private ComboBox<String> cbMarca;

    @FXML
    private Label verifier;
    @FXML
    private Pane pane;
    @FXML
    private ComboBox<String> cbModelo;
    @FXML
    private ComboBox<String> cbKim1;
    @FXML
    private ComboBox<String> cbKim2;
    @FXML
    private ComboBox<String> cbPrecio1;
    @FXML
    private ComboBox<String> cbPrecio2;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnBuscar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cargarItems(cbMarca, cbKim1, cbKim2, cbPrecio1, cbPrecio2, cbModelo);

        btnRegresar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                }
            }
        });
        btnBuscar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (cbMarca.getValue() != null
                        && cbModelo.getValue() != null
                        && cbKim1.getValue() != null
                        && cbKim2.getValue() != null
                        && cbPrecio1.getValue() != null
                        && cbPrecio2.getValue() != null) {
                    
                    SeleccionaTuAutoController.datosBusqueda=getSelected(cbMarca, cbModelo, cbKim1, cbKim2, cbPrecio1, cbPrecio2);
                        
                    try {   
                        App.setRoot("SeleccionaTuAuto");
                    } catch (IOException ex) {
                    }
                } else {
                    verifier.setText("Seleccione todas las opciones para continuar!");
                }

            }

        });

    }

    //Funciones
    private void cargarItems(
            ComboBox<String> cb1,
            ComboBox<String> cb2,
            ComboBox<String> cb3,
            ComboBox<String> cb4,
            ComboBox<String> cb5,
            ComboBox<String> cb6
    ) {

        Queue<Auto> autos;
        autos = Fichero.cargarAutos();
        getMarcas(autos, cb1);

        getKim(cb2);
        getKim(cb3);
        getPrecio(cb4);
        getPrecio(cb5);
        cbMarca.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String seleccion = cbMarca.getValue();
                getModelo(autos, seleccion, cb6);
            }
        });

    }

    private void getMarcas(Queue<Auto> list, ComboBox<String> cb) {
        Set<String> marcas = new HashSet<>();
        for (Auto auto : list) {
            marcas.add(auto.getMarca());
        }
        for (String string : marcas) {
            cb.getItems().add(string);
        }

    }

    private void getTipo(Queue<Auto> list, ComboBox<String> cb) {
        Set<String> tipo = new HashSet<>();
        for (Auto auto : list) {
            tipo.add(auto.getTipo());
        }
        for (String string : tipo) {
            cb.getItems().add(string);
        }
    }

    private void getKim(ComboBox<String> cb) {
        cb.getItems().add("0");
        cb.getItems().add("30000");
        cb.getItems().add("60000");
        cb.getItems().add("90000");
        cb.getItems().add("120000");

    }

    private void getPrecio(ComboBox<String> cb) {
        cb.getItems().add("0");
        cb.getItems().add("5000");
        cb.getItems().add("10000");
        cb.getItems().add("15000");
        cb.getItems().add("20000");
        cb.getItems().add("25000");
        cb.getItems().add("30000");

    }

    private void getModelo(Queue<Auto> list, String marca, ComboBox<String> cbModelo) {
        Set<String> modelos = new HashSet<>();
        for (Auto auto : list) {
            if (marca != null && auto.getMarca().equals(marca)) {
                modelos.add(auto.getModelo());
            }

        }
        cbModelo.getItems().clear();
        for (String string : modelos) {

            cbModelo.getItems().add(string);
        }
    }

    public String[] getSelected(ComboBox<String> cb1, ComboBox<String> cb2, ComboBox<String> cb3, ComboBox<String> cb4, ComboBox<String> cb5, ComboBox<String> cb6) {
        String[] seleccionados = {cb1.getValue(), cb2.getValue(), cb3.getValue(), cb4.getValue(), cb5.getValue(), cb6.getValue()};

        return seleccionados;
    }
}
