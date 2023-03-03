package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {
    boolean[] asientos;
    boolean[] asientosEsperados;
    int solicitados;
    Cine cine;
    boolean resultado;

    @BeforeEach
    void setUp() {
        cine = new Cine();
    }

    @Test
    void reservaButacasC1() {
        asientos = new boolean[0];
        solicitados = 3;

        ButacasException exception = assertThrows(ButacasException.class, () -> cine.reservaButacasV1(asientos, solicitados));

        assertEquals("No se puede procesar la solicitud", exception.getMessage());
    }

    @Test
    void reservaButacasC2() {
        asientos = new boolean[0];
        solicitados = 0;

        asientosEsperados = new boolean[0];

        assertDoesNotThrow(() -> resultado = cine.reservaButacasV1(asientos, solicitados));

        assertAll("GrupoTestC2",
                () -> assertFalse(resultado),
                () -> assertArrayEquals(asientos, asientos));
    }

    @Test
    void reservaButacasC3() {
        asientos = new boolean[]{false, false, false, true, true};
        solicitados = 2;

        asientosEsperados = new boolean[]{true, true, false, true, true};

        assertDoesNotThrow(() -> resultado = cine.reservaButacasV1(asientos, solicitados));

        assertAll("GrupoTestC3",
                () -> assertArrayEquals(asientosEsperados, asientos),
                () -> assertTrue(resultado));
    }

    @Test
    void reservaButacasC4() {
        asientos = new boolean[]{true, true, true};
        solicitados = 1;

        asientosEsperados = new boolean[]{true, true, true};

        assertDoesNotThrow(() -> resultado = cine.reservaButacasV1(asientos, solicitados));

        assertAll("GrupoTestC3",
                () -> assertArrayEquals(asientosEsperados, asientos),
                () -> assertFalse(resultado));
    }
}