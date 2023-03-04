package ppss.ejercicio2;

import org.junit.jupiter.api.Test;
import ppss.ejercicio2.GestorLlamadasTestable;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {
    int minutosEntrada;
    int horaEntrada;
    double resultadoEsperado;
    GestorLlamadasTestable sut;
    CalendarioStub calendarioStub;

    @Test
    void calculaConsumoC1() {
        // Preparamos los datos de entrada. Inyectamos el doble. Preparamos el resultado esperado
        minutosEntrada = 10;
        horaEntrada = 15;
        calendarioStub = new CalendarioStub(horaEntrada);
        sut = new GestorLlamadasTestable();
        sut.setCalendario(calendarioStub);
        resultadoEsperado = 208;

        // Invocamos el SUT
        double resultadoReal = sut.calculaConsumo(minutosEntrada);

        // Verificamos el resultado de la ejecución del SUT
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void calculaConsumoC2() {
        // Preparamos los datos de entrada. Inyectamos el doble. Preparamos el resultado esperado
        minutosEntrada = 10;
        horaEntrada = 22;
        calendarioStub = new CalendarioStub(horaEntrada);
        sut = new GestorLlamadasTestable();
        sut.setCalendario(calendarioStub);
        resultadoEsperado = 105;

        // Invocamos el SUT
        double resultadoReal = sut.calculaConsumo(minutosEntrada);

        // Verificamos el resultado de la ejecución del SUT
        assertEquals(resultadoEsperado, resultadoReal);
    }
}