package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.Documental;
import modelo.Investigador;

class DocumentalTest {

	@Test
    void testMostrarDetalles() {
        Documental doc = new Documental(
                "Planeta Tierra", 
                90, 
                "Naturaleza", 
                "Fauna", 
                "Robert Fisher"
        );

        assertDoesNotThrow(doc::mostrarDetalles);
    }

    @Test
    void testGetTema() {
        Documental doc = new Documental(
                "Cosmos", 
                120, 
                "Ciencia", 
                "Astronomía"
        );
        assertEquals("Astronomía", doc.getTema());
    }

    @Test
    void testSetTema() {
        Documental doc = new Documental(
                "Cosmos", 
                120, 
                "Ciencia", 
                "Astronomía"
        );
        doc.setTema("Universo");
        assertEquals("Universo", doc.getTema());
    }

    @Test
    void testGetInvestigadores() {
        Documental doc = new Documental(
                "Oceanos", 
                95, 
                "Naturaleza", 
                "Vida Marina", 
                "Sylvia Earle"
        );
        List<Investigador> investigadores = doc.getInvestigadores();

        assertEquals(1, investigadores.size());
        assertEquals("Sylvia Earle", investigadores.get(0).getNombreInves());
    }

    @Test
    void testAgregarInvestigador() {
        Documental doc = new Documental(
                "Selvas", 
                85, 
                "Naturaleza", 
                "Ecosistemas"
        );

        doc.agregarInvestigador(new Investigador("Jane Goodall"));

        assertEquals(1, doc.getInvestigadores().size());
        assertEquals("Jane Goodall", doc.getInvestigadores().get(0).getNombreInves());
    }
}