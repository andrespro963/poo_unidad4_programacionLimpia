package vista;

import modelo.*;

import java.util.List;

public class VistaConsola {

    public void mostrarPeliculas(List<Pelicula> peliculas) {
        System.out.println("=== Películas ===");
        if (peliculas == null || peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.\n");
            return;
        }
        peliculas.forEach(Pelicula::mostrarDetalles);
    }

    public void mostrarSeries(List<SerieDeTV> series) {
        System.out.println("=== Series de TV ===");
        if (series == null || series.isEmpty()) {
            System.out.println("No hay series registradas.\n");
            return;
        }
        series.forEach(SerieDeTV::mostrarDetalles);
    }

    public void mostrarDocumentales(List<Documental> documentales) {
        System.out.println("=== Documentales ===");
        if (documentales == null || documentales.isEmpty()) {
            System.out.println("No hay documentales registrados.\n");
            return;
        }
        documentales.forEach(Documental::mostrarDetalles);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}