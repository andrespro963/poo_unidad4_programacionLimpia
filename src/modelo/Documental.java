package modelo;

import java.util.*;
import java.util.stream.Collectors;

public class Documental extends ContenidoAudiovisual {
    
	private String tema;
    private final List<Investigador> investigadores;

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
        super(titulo, duracionEnMinutos, genero);
        this.tema = tema != null ? tema : "";
        this.investigadores = new ArrayList<>();
    }

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema, String nombreInvestigador) {
        this(titulo, duracionEnMinutos, genero, tema);
        if (nombreInvestigador != null && !nombreInvestigador.isBlank()) {
            agregarInvestigador(new Investigador(nombreInvestigador));
     }
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
    	if (tema != null) {
        this.tema = tema;
     }
    }
    public List<Investigador> getInvestigadores() {
        return new ArrayList<>(investigadores);
    }
    
    public void agregarInvestigador(Investigador investigador) {
    	if (investigador != null) {
    	investigadores.add(investigador);
     }
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del Documental:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Tema: " + tema);
        
        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores registrados.");
        } else {
            System.out.println("Investigadores:");
            investigadores.forEach(inv -> System.out.println(" - " + inv));
        }
        System.out.println();
    }

    public static void guardarEnCSV(String rutaArchivo, List<Documental> documentales) {  //Guardar lista de documentales en CSV
    	if (documentales == null || documentales.isEmpty()) return;
    	
    	List<String[]> datos = new ArrayList<>();
        
    	for (Documental doc : documentales) {
            String investigadoresStr = doc.investigadores.stream()
                    .map(Investigador::getNombreInves)
                    .collect(Collectors.joining(";"));

            datos.add(new String[]{
                    doc.getTitulo(),
                    String.valueOf(doc.getDuracionEnMinutos()),
                    doc.getGenero(),
                    doc.getTema(),
                    investigadoresStr
            });
        }
        GestorArchivos.escribirCSV(rutaArchivo, datos);
    }

    public static List<Documental> cargarDesdeCSV(String rutaArchivo) {     //Cargar lista de documentales desde CSV
        List<Documental> lista = new ArrayList<>();
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
                String tema = fila[3];
                String investigadoresStr = fila[4];

                Documental doc = new Documental(titulo, duracion, genero, tema);

                if (investigadoresStr != null && !investigadoresStr.isBlank()) {
                    for (String nombreInv : investigadoresStr.split(";")) {
                        if (!nombreInv.isBlank()) {
                            doc.agregarInvestigador(new Investigador(nombreInv.trim()));
                        }
                    }
                }
                lista.add(doc);

            } catch (Exception e) {
                System.err.println("Error al cargar documental desde CSV: " + e.getMessage());
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