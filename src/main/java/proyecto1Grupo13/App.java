package proyecto1Grupo13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathImg = "src/main/resources/images/";
    public static String pathImagenes = "src/main/resources/imagenes/";
    public static String pathArchivos = "src/main/resources/archivos/";
    public static String pathDescripciones = "src/main/resources/descripciones/";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static ArrayListAuto<Auto> crearArrayList(String ruta) {
        ArrayListAuto<Auto> autos = new ArrayListAuto<>();
        try (BufferedReader br = new BufferedReader(new FileReader(App.pathArchivos + ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");
                Auto auto = new Auto(
                        atributos[0], // tipo
                        atributos[1], // marca
                        atributos[2], // modelo
                        atributos[3], // color
                        Integer.valueOf(atributos[4]), // kilometraje
                        Double.valueOf(atributos[5]), // precio
                        Integer.valueOf(atributos[6]), // anio
                        atributos[7], // imagen (ruta)
                        atributos[8] // descripcion (ruta de txt)

                );
                autos.add(auto);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return autos;
    }

    public static void main(String[] args) {
        launch();
    }

}
