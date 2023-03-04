package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    TipoCoche tipoCocheEntrada;
    LocalDate fechaInicioEntrada;
    int numDiasEntrada;
    float importeEsperado;
    Ticket resultadoRealTicket;
    IService servicioStub;

    @BeforeEach
    void setUp() {
        servicioStub = new ServicioSTUB(10f);
    }

    @Test
    void calculaPrecioC1() {
        // Preparamos los datos de entrada. Preparamos el STUB. Preparamos el resultado esperado
        tipoCocheEntrada = TipoCoche.TURISMO;
        fechaInicioEntrada = LocalDate.of(2023, 02, 18);
        numDiasEntrada = 10;
        CalendarioStub calendarioStub = new CalendarioStub(new int[0], new int[0]);
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);
        importeEsperado = 75;
        Ticket resultadoEsperadoTicket = new Ticket();
        resultadoEsperadoTicket.setPrecio_final(importeEsperado);

        // Invocamos a la SUT
        assertDoesNotThrow(() ->
        {resultadoRealTicket = sut.calculaPrecio(tipoCocheEntrada, fechaInicioEntrada, numDiasEntrada);});

        // Verificamos los resultados
        assertEquals(resultadoEsperadoTicket.getPrecio_final(), resultadoRealTicket.getPrecio_final());
    }

    @Test
    void calculaPrecioC2() {
        // Preparamos los datos de entrada. Preparamos el STUB. Preparamos el resultado esperado
        tipoCocheEntrada = TipoCoche.CARAVANA;
        fechaInicioEntrada = LocalDate.of(2023, 06, 19);
        numDiasEntrada = 7;
        CalendarioStub calendarioStub = new CalendarioStub(new int[]{20, 24}, new int[0]);
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);
        importeEsperado = 62.5f;
        Ticket resultadoEsperadoTicket = new Ticket();
        resultadoEsperadoTicket.setPrecio_final(importeEsperado);

        // Invocamos a la SUT
        assertDoesNotThrow(() ->
        {resultadoRealTicket = sut.calculaPrecio(tipoCocheEntrada, fechaInicioEntrada, numDiasEntrada);});

        // Verificamos los resultados
        assertEquals(resultadoEsperadoTicket.getPrecio_final(), resultadoRealTicket.getPrecio_final());
    }

    @Test
    void calculaPrecioC3() {
        // Preparamos los datos de entrada. Preparamos el STUB. Preparamos el resultado esperado
        tipoCocheEntrada = TipoCoche.TURISMO;
        fechaInicioEntrada = LocalDate.of(2023, 04, 17);
        numDiasEntrada = 8;
        CalendarioStub calendarioStub = new CalendarioStub(new int[0], new int[]{18, 21, 22});
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);

        // Invocamos a la SUT
        MensajeException e = assertThrows(MensajeException.class, () ->
        {resultadoRealTicket = sut.calculaPrecio(tipoCocheEntrada, fechaInicioEntrada, numDiasEntrada);});

        // Verificamos el mensaje devuelvo por la excepcion
        assertEquals("Error en dia: 2023-04-18; Error en dia: 2023-04-21; Error en dia: 2023-04-22; ", e.getMessage());
    }
}