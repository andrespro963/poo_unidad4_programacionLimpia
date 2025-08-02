package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.SerieDeTV;
import modelo.Temporada;

class SerieDeTVTest {

    @Test
    void testMostrarDetalles() {
        SerieDeTV serie = new SerieDeTV("Breaking Bad", 50, "Drama", 5);
        serie.agregarTemporada(new Temporada(1, 7));

        // Solo comprobamos que no lance excepción al mostrar
        assertDoesNotThrow(serie::mostrarDetalles);
    }

    @Test
    void testAgregarTemporada() {
        SerieDeTV serie = new SerieDeTV("Dark", 60, "Sci-Fi", 3);
        serie.agregarTemporada(new Temporada(1, 10));

        assertEquals(1, serie.getListaTemporadas().size());
        assertEquals(10, serie.getListaTemporadas().get(0).getEpisodios());
    }

    @Test
    void testGetListaTemporadas() {
        SerieDeTV serie = new SerieDeTV("Lupin", 45, "Misterio", 2);
        serie.agregarTemporada(new Temporada(1, 5));
        serie.agregarTemporada(new Temporada(2, 7));

        List<Temporada> temporadas = serie.getListaTemporadas();
        assertEquals(2, temporadas.size());
        assertEquals(5, temporadas.get(0).getEpisodios());
        assertEquals(7, temporadas.get(1).getEpisodios());
    }

    @Test
    void testGetTemporadas() {
        SerieDeTV serie = new SerieDeTV("Friends", 22, "Comedia", 10);
        assertEquals(10, serie.getTemporadas());
    }

    @Test
    void testSetTemporadas() {
        SerieDeTV serie = new SerieDeTV("Friends", 22, "Comedia", 10);
        serie.setTemporadas(5);
        assertEquals(5, serie.getTemporadas());
    }
}