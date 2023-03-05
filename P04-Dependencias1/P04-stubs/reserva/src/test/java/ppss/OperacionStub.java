package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.Arrays;

public class OperacionStub implements IOperacionBO {
    private String[] isbnValidos;
    private String[] sociosValidos;
    private String[] isbnJDBCException;

    public OperacionStub(String[] isbnValidos, String[] sociosValidos) {
        this.isbnValidos = isbnValidos;
        this.sociosValidos = sociosValidos;
        isbnJDBCException = new String[0];
    }

    public void setIsbnJDBCException(String[] isbnJDBCException) {
        this.isbnJDBCException = isbnJDBCException;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if (Arrays.asList(isbnJDBCException).contains(isbn))
            throw new JDBCException("CONEXION invalida;");
        if (!Arrays.asList(sociosValidos).contains(socio))
            throw new SocioInvalidoException("SOCIO invalido; ");
        if (!Arrays.asList(isbnValidos).contains(isbn))
            throw new IsbnInvalidoException("ISBN invalido:" + isbn);

    }
}
