package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class DataArrayTest {
    DataArray dataArray;
    int elementoABorrar;
    int[] colecciónEntrada;
    int[] colecciónEsperada;
    int numElementosEsperados;

    @Test
    void deleteC1() {
        colecciónEntrada = new int[]{1, 3, 5, 7};
        elementoABorrar = 5;
        dataArray = new DataArray(colecciónEntrada);

        colecciónEsperada = new int[]{1, 3, 7};
        numElementosEsperados = 3;

        assertDoesNotThrow(() -> dataArray.delete(elementoABorrar));

        assertAll("GrupoC1",
                () -> assertArrayEquals(colecciónEsperada, dataArray.getColeccion()),
                () -> assertEquals(numElementosEsperados, dataArray.size()));
    }

    @Test
    void deleteC2() {
        colecciónEntrada = new int[]{1, 3, 3, 5, 7};
        elementoABorrar = 3;
        dataArray = new DataArray(colecciónEntrada);

        colecciónEsperada = new int[]{1, 3, 5, 7};
        numElementosEsperados = 4;

        assertDoesNotThrow(() -> dataArray.delete(elementoABorrar));

        assertAll("GrupoC1",
                () -> assertArrayEquals(colecciónEsperada, dataArray.getColeccion()),
                () -> assertEquals(numElementosEsperados, dataArray.size()));
    }

    @Test
    void deleteC3() {
        colecciónEntrada = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        elementoABorrar = 4;
        dataArray = new DataArray(colecciónEntrada);

        colecciónEsperada = new int[]{1, 2, 3, 5, 6, 7, 8, 9, 10};
        numElementosEsperados = 9;

        assertDoesNotThrow(() -> dataArray.delete(elementoABorrar));

        assertAll("GrupoC1",
                () -> assertArrayEquals(colecciónEsperada, dataArray.getColeccion()),
                () -> assertEquals(numElementosEsperados, dataArray.size()));
    }

    @Test
    void deleteC4() {
        elementoABorrar = 8;
        dataArray = new DataArray();

        DataException exception = assertThrows(DataException.class ,() -> dataArray.delete(elementoABorrar));

        assertEquals("No hay elementos en la colección", exception.getMessage());
    }

    @Test
    void deleteC5() {
        colecciónEntrada = new int[]{1, 3, 5, 7,};
        elementoABorrar = -5;
        dataArray = new DataArray(colecciónEntrada);

        DataException exception = assertThrows(DataException.class ,() -> dataArray.delete(elementoABorrar));

        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    void deleteC6() {
        elementoABorrar = 0;
        dataArray = new DataArray();

        DataException exception = assertThrows(DataException.class ,() -> dataArray.delete(elementoABorrar));

        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    void deleteC7() {
        colecciónEntrada = new int[]{1, 3, 5, 7,};
        elementoABorrar = 8;
        dataArray = new DataArray(colecciónEntrada);

        DataException exception = assertThrows(DataException.class ,() -> dataArray.delete(elementoABorrar));

        assertEquals("Elemento no encontrado", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("casosDePruebaC8")
    @Tag("parametrizado")
    @Tag("conExcepciones")
    void testParametrizadoC8(int[] colecciónEntrada, int elementoABorrar, String mensajeExcepcion) {
        dataArray = new DataArray(colecciónEntrada);

        DataException exception = assertThrows(DataException.class ,() -> dataArray.delete(elementoABorrar));
        assertEquals(mensajeExcepcion, exception.getMessage());
    }
    private static Stream<Arguments> casosDePruebaC8() {
        return Stream.of(
                Arguments.of(new int[0], 8, "No hay elementos en la colección"),
                Arguments.of(new int[]{1, 3, 5, 7}, -5, "El valor a borrar debe ser > 0"),
                Arguments.of(new int[0], 0, "Colección vacía. Y el valor a borrar debe ser > 0"),
                Arguments.of(new int[]{1, 3, 5, 7}, 8, "Elemento no encontrado")
        );
    }

    @ParameterizedTest
    @MethodSource("casosDePruebaC9")
    @Tag("parametrizado")
    void testParametrizadoC9(int[] colecciónEntrada, int elementoABorrar, int[] colecciónEsperada, int numElementosEsperados) {
        dataArray = new DataArray(colecciónEntrada);

        assertDoesNotThrow(() -> dataArray.delete(elementoABorrar));

        assertAll("GrupoC1",
                () -> assertArrayEquals(colecciónEsperada, dataArray.getColeccion()),
                () -> assertEquals(numElementosEsperados, dataArray.size()));
    }
    private static Stream<Arguments> casosDePruebaC9() {
        return Stream.of(
                Arguments.of(new int[]{1,3,5,7}, 5, new int[]{1,3,7}, 3),
                Arguments.of(new int[]{1,3,3,5,7}, 3, new int[]{1,3,5,7}, 4),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 4, new int[]{1,2,3,5,6,7,8,9,10}, 9));
    }

}
