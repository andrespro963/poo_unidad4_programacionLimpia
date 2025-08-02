package modelo;

public class Temporada {
	private int numero;
	private int episodios;

	public Temporada(int numero, int episodios) {
        this.numero = Math.max(numero, 0);
        this.episodios = Math.max(episodios, 0);
}
	// Getters y setters
	public int getNumero() {
		return numero;
	}

	public int getEpisodios() {
		return episodios;
	}

 @Override
 public String toString() {
     return "Temporada " + numero + " (" + episodios + " episodios)";
    }
}