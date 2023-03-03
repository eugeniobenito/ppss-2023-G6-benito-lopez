package ppss.ejercicio1;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    @Test
    void calculaConsumoC1() {
        // Preparamos datos de entrada
        int minutosEntrada = 10;
        int horaEntrada = 15;
        double resultadoEsperado = 208;
        GestorLlamadasTestable sut = new GestorLlamadasTestable();
        sut.setResultado(horaEntrada);

        // Invocamos el SUT
        double resultadoReal = sut.calculaConsumo(minutosEntrada);

        // Verificamos resultados
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void calculaConsumoC2() {
        // Preparamos datos de entrada
        int minutosEntrada = 10;
        int horaEntrada = 22;
        double resultadoEsperado = 105;
        GestorLlamadasTestable sut = new GestorLlamadasTestable();
        sut.setResultado(horaEntrada);

        // Invocamos el SUT
        double resultadoReal = sut.calculaConsumo(minutosEntrada);

        // Verificamos resultados
        assertEquals(resultadoEsperado, resultadoReal);
    }

    // Pese a que no lo indique el enunciado, no estoy seguro de si lo
    // correcto sería hacer un test parametrizado. Por si acaso, aquí queda
    @ParameterizedTest
    @MethodSource("casosDePruebaC3")
    @Tag("parametrizado")
    void testParametrizadoC3(int minutosEntrada, int horaEntrada, double resultadoEsperado) {
        GestorLlamadasTestable sut = new GestorLlamadasTestable();
        sut.setResultado(horaEntrada);

        double resultadoReal = sut.calculaConsumo(minutosEntrada);

        assertEquals(resultadoEsperado, resultadoReal);
    }
    private static Stream<Arguments> casosDePruebaC3() {
        return Stream.of(
                Arguments.of(10, 15, 208),
                Arguments.of(10, 22, 105));
    }
}