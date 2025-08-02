package controlador;

import modelo.*;
import vista.VistaConsola;

import java.util.List;

public class ControladorContenido {

    private final VistaConsola vista;

    public ControladorContenido(VistaConsola vista) {
        this.vista = vista;
    }

    public void cargarYMostrarContenido() {
        List<Pelicula> peliculas = Pelicula.cargarDesdeCSV("data/peliculas.csv");
        List<SerieDeTV> series = SerieDeTV.cargarDesdeCSV("data/series.csv");
        List<Documental> documentales = Documental.cargarDesdeCSV("data/documentales.csv");

        vista.mostrarPeliculas(peliculas);
        vista.mostrarSeries(series);
        vista.mostrarDocumentales(documentales);
    }

    public void guardarContenido(List<Pelicula> peliculas, List<SerieDeTV> series, List<Documental> documentales) {
        Pelicula.guardarEnCSV("guardar/peliculasGeneradas.csv", peliculas);
        SerieDeTV.guardarEnCSV("guardar/seriesGeneradas.csv", series);
        Documental.guardarEnCSV("guardar/documentalesGenerados.csv", documentales);
        vista.mostrarMensaje("Contenido guardado correctamente en la carpeta 'guardar/'.");
    }
}