/**
 * Class SerieDeTV
 */
package modelo;

import java.util.*;
import java.util.stream.Collectors;

public class SerieDeTV extends ContenidoAudiovisual {
	
    private int temporadas;
    
    private final List<Temporada> listaTemporadas;

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int temporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = Math.max(temporadas,0);
        this.listaTemporadas = new ArrayList<>();
    }

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int temporadas, int numero, int episodios) {
        this(titulo, duracionEnMinutos, genero, temporadas);
        agregarTemporada(new Temporada(temporadas, episodios));
    }

    //Getters y Setters
    public void agregarTemporada(Temporada temporada) {
        if (temporada != null) {
            listaTemporadas.add(temporada);
        }
    }
    public List<Temporada> getListaTemporadas() {
        return new ArrayList<>(listaTemporadas);
    }
    
    public int getTemporadas() {
        return temporadas;
    }
    
    public void setTemporadas(int temporadas) {
    	if (temporadas >= 0) {
        this.temporadas = temporadas;
     }
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la SerieDeTv:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Temporadas: " + this.temporadas);
        System.out.println("Temporadas Detalle:");
        
        if (listaTemporadas.isEmpty()) {
            System.out.println("No hay temporadas registradas.");
        } else {
            System.out.println("Temporadas:");
            listaTemporadas.forEach(t -> System.out.println(" - " + t));
        }
            System.out.println();
    }

    public static void guardarEnCSV(String rutaArchivo, List<SerieDeTV> series) { //Guardar lista de Series a CSV
    	if (series == null || series.isEmpty()) return;
    	
    	List<String[]> datos = new ArrayList<>();
        
        for (SerieDeTV serie : series) {
            String temporadasStr = serie.listaTemporadas.stream()
                    .map(t -> t.getNumero() + "-" + t.getEpisodios())
                    .collect(Collectors.joining(";"));

            datos.add(new String[]{
                    serie.getTitulo(),
                    String.valueOf(serie.getDuracionEnMinutos()),
                    serie.getGenero(),
                    String.valueOf(serie.getTemporadas()),
                    temporadasStr
            });
        }
        GestorArchivos.escribirCSV(rutaArchivo, datos);
    }

    public static List<SerieDeTV> cargarDesdeCSV(String rutaArchivo) {     //Cargar lista de Series desde CSV
        List<SerieDeTV> lista = new ArrayList<>();
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
                    int duracion = parseEnteroSeguro(fila[1]);
                    String genero = fila[2];
                    int temporadas = parseEnteroSeguro(fila[3]);
                    String temporadasStr = fila[4];

                    SerieDeTV serie = new SerieDeTV(titulo, duracion, genero, temporadas);

                    if (temporadasStr != null && !temporadasStr.isBlank()) {
                        for (String tempData : temporadasStr.split(";")) {
                            String[] partes = tempData.split("-");
                            if (partes.length == 2) {
                                int numTemp = parseEnteroSeguro(partes[0]);
                                int episodios = parseEnteroSeguro(partes[1]);
                                serie.agregarTemporada(new Temporada(numTemp, episodios));
                            }
                    }
                }
                lista.add(serie);
            }catch (Exception e) {
                System.err.println("Error al cargar serie desde CSV: " + e.getMessage());
            }
        }
        return lista;
    }
  
private static int parseEnteroSeguro(String valor) {
    try {
        return Integer.parseInt(valor.replaceAll("\"", "").trim());
    } catch (NumberFormatException e) {
        System.err.println("Valor numérico inválido en CSV: '" + valor + "'. Se usará 0.");
        return 0;
    }
 }
}