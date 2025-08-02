package modelo;


public class Investigador {
    private String nombreInves;

    public Investigador(String nombreInves) {
        this.nombreInves = nombreInves != null ? nombreInves.trim() : "";
    }

    public String getNombreInves() {
        return nombreInves;
    }

    public void setNombreInves(String nombreInves) {
        if (nombreInves != null && !nombreInves.isBlank()) {
            this.nombreInves = nombreInves.trim();
        }
    }

    @Override
    public String toString() {
        return nombreInves;
    }
}