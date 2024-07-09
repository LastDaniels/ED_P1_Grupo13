package proyecto1.grupo13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Fichero {

    public static Queue<String> leer(String rutaArchivo) {
        Queue<String> lineas = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.offer(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    public static boolean escribir(String ruta, String linea) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(linea+"\n");
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo " + ruta);
            return false;
        }

        return true;
    }
    
    public static boolean sobreEscribir(String ruta, String linea) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, false))) {
            writer.write(linea+"\n");
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo " + ruta);
            return false;
        }

        return true;
    }
    

    public static Queue<Auto> cargarAutos() {

        Queue<Auto> autos = new LinkedList<>();
        Queue<String> datos = Fichero.leer("src/main/resources/archivos/autos.txt");
        for (String dato : datos) {
            String[] line = dato.split(",");
            Auto a = new Auto(line[0], line[1], line[2], line[3], Integer.valueOf(line[4]), Double.parseDouble(line[5]), Integer.valueOf(line[6]), line[7], line[8]);
            autos.add(a);
        }
        return autos;
    }
}
