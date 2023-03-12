package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {
    private int minutosEntrada;
    private double resultadoEsperado;
    private double resultadoReal;

    @Test
    void calculaConsumoC1() {
        /* Preparar los datos de entrada */
        minutosEntrada = 22;
        resultadoEsperado = 457.6;

        // Crear los mocks
        IMocksControl ctrl = EasyMock.createStrictControl();
        Calendario calendarioMock = ctrl.mock(Calendario.class);
        GestorLlamadas gestorLlamadasMock = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        // Programar las expectativas
        EasyMock.expect(gestorLlamadasMock.getCalendario()).andReturn(calendarioMock);
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(10);
        // Indicar al Framwork que el mock está listo
        ctrl.replay();

        /* Ejecutar la SUT */
        resultadoReal = gestorLlamadasMock.calculaConsumo(minutosEntrada);

        /* Verificar las expectativas del mock */
        ctrl.verify();

        /* Verificar el resultado de ejercitar la SUT */
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void calculaConsumoC2() {
        /* Preparar los datos de entrada */
        minutosEntrada = 13;
        resultadoEsperado = 136.5;

        // Crear los mocks
        IMocksControl ctrl = EasyMock.createStrictControl();
        Calendario calendarioMock = ctrl.mock(Calendario.class);
        GestorLlamadas gestorLlamadasMock = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        // Programar las expectativas
        EasyMock.expect(gestorLlamadasMock.getCalendario()).andReturn(calendarioMock);
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(21);
        // Indicar al Framwork que el mock está listo
        ctrl.replay();

        /* Ejecutar la SUT */
        resultadoReal = gestorLlamadasMock.calculaConsumo(minutosEntrada);

        /* Verificar las expectativas del mock */
        ctrl.verify();

        /* Verificar el resultado de ejercitar la SUT */
        assertEquals(resultadoEsperado, resultadoReal);
    }
}