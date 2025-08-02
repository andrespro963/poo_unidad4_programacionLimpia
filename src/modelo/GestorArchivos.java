package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {

    public static List<String[]> leerCSV(String rutaArchivo) {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                datos.add(linea.split(","));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo CSV: " + e.getMessage());
        }
        return datos;
    }

    public static void escribirCSV(String rutaArchivo, List<String[]> datos) {
        File archivo = new File(rutaArchivo);
        archivo.getParentFile().mkdirs(); // Crear carpetas si no existen
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (String[] fila : datos) {
                pw.println(String.join(",", fila));
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo CSV: " + e.getMessage());
        }
    }
}