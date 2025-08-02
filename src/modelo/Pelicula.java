/**
 * Class Pelicula
 */

package modelo;

import java.util.*;

public class Pelicula extends ContenidoAudiovisual {
    
	private String estudio;
    private final List<Actor> actores;

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio != null ? estudio : "";
        this.actores = new ArrayList<>();
    }

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio, String nombreActor) { // Constructor con 5 parámetros
        this(titulo, duracionEnMinutos, genero, estudio); 
        if (nombreActor != null && !nombreActor.isBlank()) {//valida datos null y vacios
            agregarActor(new Actor(nombreActor));
        }
    }
    
    public void agregarActor(Actor actor) {
        if (actor != null) {
            actores.add(actor);
        }
    }
    
    public String getEstudio() {
        return estudio;
    }
    
    public void setEstudio(String estudio) {
    	if (estudio != null) {
        this.estudio = estudio;
    	}
    }

    public List<Actor> getActores() {
        return new ArrayList<>(actores);
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio: " + getEstudio());
        System.out.println("Actores:");
        
        if (actores.isEmpty()) {
            System.out.println(" - No hay actores registrados.");
        } else {
            actores.forEach(actor -> System.out.println(" - " + actor));
        }
        System.out.println();
    }

    public static void guardarEnCSV(String rutaArchivo, List<Pelicula> peliculas) {  // Guardar lista de películas a CSV
    	if (peliculas == null || peliculas.isEmpty()) return;
    	 
    	List<String[]> datos = new ArrayList<>();
        
    	for (Pelicula p : peliculas) {
            String actoresStr = "";
            for (Actor a : p.actores) {
                if (!actoresStr.isEmpty()) actoresStr += ";";
                actoresStr += a.getNombre();
            }
            datos.add(new String[]{
                    p.getTitulo(),
                    String.valueOf(p.getDuracionEnMinutos()),
                    p.getGenero(),
                    p.getEstudio(),
                    actoresStr
            });
        }
        GestorArchivos.escribirCSV(rutaArchivo, datos);
    }

    public static List<Pelicula> cargarDesdeCSV(String rutaArchivo) {    // Cargar lista de películas desde CSV
        List<Pelicula> lista = new ArrayList<>();
        List<String[]> datos = GestorArchivos.leerCSV(rutaArchivo);

        boolean primeraFila = true;
        for (String[] fila : datos) {
            if (primeraFila) { // saltar encabezado
                primeraFila = false;
                continue;
            }
        	
            if (fila.length < 5) continue;

            try {
            	
                String titulo = fila[0];
                int duracion = Integer.parseInt(fila[1].replaceAll("\"", "").trim());
                String genero = fila[2];
                String estudio = fila[3];
                String actoresStr = fila[4];

                Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
                
                if (actoresStr != null && !actoresStr.isBlank()) {
                    for (String nombreActor : actoresStr.split(";")) {
                        if (!nombreActor.isBlank()) {
                            pelicula.agregarActor(new Actor(nombreActor.trim()));
                        }
                    }
                }
                lista.add(pelicula);

            } catch (NumberFormatException e) {
                System.err.println("Error al parsear la duración en CSV: " + e.getMessage());
            }
        }
        return lista;
    }
    
    private static int parseEnteroSeguro(String valor) {
        try {
            return Integer.parseInt(valor.replace("\"", "").trim());
        } catch (NumberFormatException e) {
            System.err.println("Valor numérico inválido en CSV: '" + valor + "'. Se usará 0.");
            return 0;
        }
    }
}