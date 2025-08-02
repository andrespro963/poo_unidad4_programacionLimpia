package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.Actor;
import modelo.Pelicula;

class PeliculaTest {

	 @Test
	    void testMostrarDetalles() {
	        Pelicula peli = new Pelicula("Matrix", 136, "Acción", "Warner", "Keanu Reeves");

	        assertDoesNotThrow(peli::mostrarDetalles);
	    }

	    @Test
	    void testAgregarActor() {
	        Pelicula peli = new Pelicula("Matrix", 136, "Acción", "Warner");
	        Actor actor = new Actor("Laurence Fishburne");
	        peli.agregarActor(actor);

	        assertEquals(1, peli.getActores().size());
	        assertEquals("Laurence Fishburne", peli.getActores().get(0).getNombre());
	    }

	    @Test
	    void testGetEstudio() {
	        Pelicula peli = new Pelicula("Interstellar", 169, "Sci-Fi", "Paramount");
	        assertEquals("Paramount", peli.getEstudio());
	    }

	    @Test
	    void testSetEstudio() {
	        Pelicula peli = new Pelicula("Interstellar", 169, "Sci-Fi", "Paramount");
	        peli.setEstudio("Warner Bros");
	        assertEquals("Warner Bros", peli.getEstudio());
	    }

	    @Test
	    void testGetActores() {
	        Pelicula peli = new Pelicula("Matrix", 136, "Acción", "Warner", "Keanu Reeves");
	        List<Actor> actores = peli.getActores();
	        assertEquals(1, actores.size());
	        assertEquals("Keanu Reeves", actores.get(0).getNombre());
	    }
}