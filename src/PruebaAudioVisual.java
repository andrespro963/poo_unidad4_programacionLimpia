import java.util.List;

import controlador.ControladorContenido;
import modelo.Documental;
import modelo.Pelicula;
import modelo.SerieDeTV;
import vista.VistaConsola;

public class PruebaAudioVisual {
	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		ControladorContenido controlador = new ControladorContenido(vista);

		// Cargar y mostrar
		controlador.cargarYMostrarContenido();

		// Guardar contenido cargado
		List<Pelicula> peliculas = Pelicula.cargarDesdeCSV("data/peliculas.csv");
		List<SerieDeTV> series = SerieDeTV.cargarDesdeCSV("data/series.csv");
		List<Documental> documentales = Documental.cargarDesdeCSV("data/documentales.csv");

		controlador.guardarContenido(peliculas, series, documentales);
	}
}