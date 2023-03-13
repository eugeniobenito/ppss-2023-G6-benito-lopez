package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremioTest {
    String resultadoEsperado;
    String resultadoReal;
    Premio premioMock;
    ClienteWebService clienteMock;
    @Test
    void compruebaPremioC1() {
        /* Preparar los datos de entrada */
        resultadoEsperado = "Premiado con entrada final Champions";
        // Crear los mocks
        IMocksControl ctrl = EasyMock.createStrictControl();
        premioMock = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        clienteMock = ctrl.mock(ClienteWebService.class);
        // Programar las expectativas
        EasyMock.expect(premioMock.generaNumero()).andReturn(0.07f);
        assertDoesNotThrow(() -> {EasyMock.expect(clienteMock.obtenerPremio()).andReturn("entrada final Champions");});
        // Indicar al Framework que está listo
        ctrl.replay();
        /* Invocar a la SUT */
        premioMock.cliente = clienteMock;
        resultadoReal = premioMock.compruebaPremio();
        /* Verificar el comportamiento del mock */
        ctrl.verify();
        /* Verificar los resultados de ejercitar la SUT */
        assertEquals(resultadoReal, resultadoEsperado);
    }

    @Test
    void compruebaPremioC2() {
        /* Preparar los datos de entrada */
        resultadoEsperado = "No se ha podido obtener el premio";
        // Crear los mocks
        IMocksControl ctrl = EasyMock.createStrictControl();
        premioMock = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        clienteMock = ctrl.mock(ClienteWebService.class);
        // Programar las expectativas
        EasyMock.expect(premioMock.generaNumero()).andReturn(0.03f);
        assertDoesNotThrow(() -> {EasyMock.expect(clienteMock.obtenerPremio()).andThrow(new ClienteWebServiceException());});
        // Indicar al Framework que está listo
        ctrl.replay();
        /* Invocar a la SUT */
        premioMock.cliente = clienteMock;
        resultadoReal = premioMock.compruebaPremio();
        /* Verificar el comportamiento del mock */
        ctrl.verify();
        /* Verificar los resultados de ejercitar la SUT */
        assertEquals(resultadoReal, resultadoEsperado);
    }

    @Test
    void compruebaPremioC3() {
        /* Preparar los datos de entrada */
        resultadoEsperado = "Sin premio";
        // Crear los mocks
        premioMock = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock();
        // Programar las expectativas
        EasyMock.expect(premioMock.generaNumero()).andReturn(0.3f);
        // Indicar al Framework que está listo
        EasyMock.replay(premioMock);
        /* Invocar a la SUT */
        premioMock.cliente = clienteMock;
        resultadoReal = premioMock.compruebaPremio();
        /* Verificar el comportamiento del mock */
        EasyMock.verify(premioMock);
        /* Verificar los resultados de ejercitar la SUT */
        assertEquals(resultadoReal, resultadoEsperado);
    }
}