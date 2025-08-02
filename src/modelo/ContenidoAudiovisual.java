package modelo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase base para todo contenido audiovisual.
 */
public abstract class ContenidoAudiovisual {
    private static final AtomicInteger contador = new AtomicInteger(1);

    private final int id;
    private String titulo;
    private int duracionEnMinutos;
    private String genero;

    public ContenidoAudiovisual(String titulo, int duracionEnMinutos, String genero) {
        this.id = contador.getAndIncrement();
        this.titulo = titulo != null ? titulo.trim() : "";
        this.duracionEnMinutos = Math.max(duracionEnMinutos, 0);
        this.genero = genero != null ? genero.trim() : "";
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getDuracionEnMinutos() { return duracionEnMinutos; }
    public String getGenero() { return genero; }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isBlank()) this.titulo = titulo.trim();
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        if (duracionEnMinutos >= 0) this.duracionEnMinutos = duracionEnMinutos;
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.isBlank()) this.genero = genero.trim();
    }

    public abstract void mostrarDetalles();
}