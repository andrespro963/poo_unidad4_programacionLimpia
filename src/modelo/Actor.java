package modelo;

public class Actor {
	private String nombre;

	public Actor(String nombre) {
		this.nombre = nombre != null ? nombre.trim() : "";
	}

	// Getters y setters	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null && !nombre.isBlank()) {
			this.nombre = nombre.trim();
	}
}
@Override
	 public String toString() {
	     return nombre;  // Imprimir el nombre del actor
	}
	
}