package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FicheroTextoTest {
    FicheroTexto ficheroTexto;
    String nombreFichero;
    int resultado;
    int resultadoEsperado;

    @BeforeEach
    void setUp() {
        ficheroTexto = new FicheroTexto();
    }

    @Test
    void contarCaracteresC1() {
        nombreFichero = "ficheroC1.txt";

        FicheroException exception = assertThrows(FicheroException.class, () -> ficheroTexto.contarCaracteres(nombreFichero));

        assertEquals("ficheroC1.txt (No existe el archivo o el directorio)", exception.getMessage());
    }

    @Test
    void contarCaracteresC2() {
        nombreFichero = "src/test/resources/ficheroCorrecto.txt";
        resultadoEsperado = 3;

        assertDoesNotThrow(() -> resultado = ficheroTexto.contarCaracteres(nombreFichero));

        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    @Tag("excluido")
    void contarCaracteresC3() {
        fail("Complete test");
    }

    @Test
    @Tag("excluido")
    void contarCaracteresC4() {
        fail("Complete test");
    }
}