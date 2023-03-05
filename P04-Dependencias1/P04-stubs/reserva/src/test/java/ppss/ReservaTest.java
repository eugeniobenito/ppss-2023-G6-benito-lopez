package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    String loginEntrada;
    String passwordEntrada;
    String identSocioEntrada;
    String[] isbnEntrada;
    String[] isbnValidos;
    String[] sociosValidos;
    private Map<String, String> credencialesCorrectas;
    ReservaTestable sut;
    OperacionStub ioSTUB;


    @BeforeEach
    void setUp() {
        isbnValidos = new String[]{"11111", "22222"};
        sociosValidos = new String[]{"Luis"};
        credencialesCorrectas = new HashMap<>();
        credencialesCorrectas.put("ppss", "ppss");
        ioSTUB = new OperacionStub(isbnValidos, sociosValidos);
        Factoria.setIOperacionBO(ioSTUB);
        sut = new ReservaTestable();
        sut.setCredencialesCorrectas(credencialesCorrectas);
    }

    @Test
    void realizaReservaC1() {
        // Preparamos datos de entrada. Preparamos el STUB. Inyectamos el STUB a través de la Factoría.
        loginEntrada = "xxxx";
        passwordEntrada = "xxxx";
        identSocioEntrada = "Luis";
        isbnEntrada = new String[]{"11111"};

        // Invocamos el STUB
        ReservaException e = assertThrows(ReservaException.class, () -> sut.realizaReserva(loginEntrada, passwordEntrada, identSocioEntrada, isbnEntrada));

        // Verificamos el mensaje
        assertEquals("ERROR de permisos; ", e.getMessage());
    }

    @Test
    void realizaReservaC2() {
        // Preparamos datos de entrada. Preparamos el STUB. Inyectamos el STUB a través de la Factoría.
        loginEntrada = "ppss";
        passwordEntrada = "ppss";
        identSocioEntrada = "Luis";
        isbnEntrada = new String[]{"11111", "22222"};

        // Invocamos el STUB. La verificación es comprobar que no se lanzan excepciones
        assertDoesNotThrow(() -> sut.realizaReserva(loginEntrada, passwordEntrada, identSocioEntrada, isbnEntrada));
    }

    @Test
    void realizaReservaC3() {
        // Preparamos datos de entrada. Preparamos el STUB. Inyectamos el STUB a través de la Factoría.
        loginEntrada = "ppss";
        passwordEntrada = "ppss";
        identSocioEntrada = "Luis";
        isbnEntrada = new String[]{"11111", "33333", "44444"};

        // Invocamos el STUB
        ReservaException e = assertThrows(ReservaException.class, () -> sut.realizaReserva(loginEntrada, passwordEntrada, identSocioEntrada, isbnEntrada));

        // Verificamos el mensaje
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", e.getMessage());
    }

    @Test
    void realizaReservaC4() {
        // Preparamos datos de entrada. Preparamos el STUB. Inyectamos el STUB a través de la Factoría.
        loginEntrada = "ppss";
        passwordEntrada = "ppss";
        identSocioEntrada = "Pepe";
        isbnEntrada = new String[]{"11111"};

        // Invocamos el STUB
        ReservaException e = assertThrows(ReservaException.class, () -> sut.realizaReserva(loginEntrada, passwordEntrada, identSocioEntrada, isbnEntrada));

        // Verificamos el mensaje
        assertEquals("SOCIO invalido; ", e.getMessage());
    }

    @Test
    void realizaReservaC5() {
        // Preparamos datos de entrada. Preparamos el STUB. Inyectamos el STUB a través de la Factoría.
        loginEntrada = "ppss";
        passwordEntrada = "ppss";
        identSocioEntrada = "Luis";
        isbnEntrada = new String[]{"11111", "22222"};
        String[] isbnJDBCException = new String[]{"22222"};
        ioSTUB.setIsbnJDBCException(isbnJDBCException);

        // Invocamos el STUB
        ReservaException e = assertThrows(ReservaException.class, () -> sut.realizaReserva(loginEntrada, passwordEntrada, identSocioEntrada, isbnEntrada));

        // Verificamos el mensaje
        assertEquals("CONEXION invalida; ", e.getMessage());
    }
}