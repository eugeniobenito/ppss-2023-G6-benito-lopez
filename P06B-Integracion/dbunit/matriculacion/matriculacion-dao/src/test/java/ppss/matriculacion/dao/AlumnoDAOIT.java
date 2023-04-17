package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.*;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

class AlumnoDAOIT {
    private IAlumnoDAO alumnoDAO; // SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;


    @BeforeEach
    void setUp() throws Exception {
        String cadena_conezionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";

        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", cadena_conezionDB, "eugenio", "22162927");
        connection = databaseTester.getConnection();

        alumnoDAO = new FactoriaDAO().getAlumnoDAO();

        // Inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA1() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        LocalDate fecha_nacimiento = LocalDate.of(1985,2,22);
        alumno.setFechaNacimiento(fecha_nacimiento);

        // Inicializamos la BD
        /*
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        */

        // Invocacion de la SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.addAlumno(alumno));

        // Recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataset = connection.createDataSet();
        ITable actualTable= databaseDataset.getTable("alumnos");

        // Creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        // Verificamos las tablas
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        LocalDate fecha_nacimiento = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fecha_nacimiento);

        // Invocacion de la SUT verificando que se lanza la expeción DAOException
        DAOException exception = Assertions.assertThrows(DAOException.class, () -> alumnoDAO.addAlumno(alumno));

        // Verificamos el mensaje de la excepción
        Assertions.assertEquals(exception.getMessage(), "Error al conectar con BD");
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA3() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        //alumno.setNombre(null);
        LocalDate fecha_nacimiento = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fecha_nacimiento);

        // Invocacion de la SUT verificando que se lanza la expeción DAOException
        DAOException exception = Assertions.assertThrows(DAOException.class, () -> alumnoDAO.addAlumno(alumno));

        // Verificamos el mensaje de la excepción
        Assertions.assertEquals(exception.getMessage(), "Error al conectar con BD");
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA4() throws Exception {
        AlumnoTO alumno = null;

        // Inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocacion de la SUT verificando que se lanza la expeción DAOException
        DAOException exception = Assertions.assertThrows(DAOException.class, () -> alumnoDAO.addAlumno(alumno));


        // Verificamos el mensaje de la excepción
        Assertions.assertEquals(exception.getMessage(), "Alumno nulo");
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA5() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        //alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        LocalDate fecha_nacimiento = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fecha_nacimiento);

        // Invocacion de la SUT verificando que se lanza la expeción DAOException
        DAOException exception = Assertions.assertThrows(DAOException.class, () -> alumnoDAO.addAlumno(alumno));

        // Verificamos el mensaje de la excepción
        Assertions.assertEquals(exception.getMessage(), "Error al conectar con BD");
    }

    @Test
    @Tag("Integracion-fase1")
    public void testB1() throws Exception {
        String nif = "11111111A";

        // Invocacion de la SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.delAlumno(nif));

        // Recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataset = connection.createDataSet();
        ITable actualTable= databaseDataset.getTable("alumnos");

        // Creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        // Verificamos las tablas
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testB2() throws Exception {
        String nif = "33333333C";

        // Invocacion de la SUT verificando que se lanza la expeción DAOException
        DAOException exception = Assertions.assertThrows(DAOException.class, () -> alumnoDAO.delAlumno(nif));

        // Verificamos el mensaje de la excepción
        Assertions.assertEquals(exception.getMessage(), "No se ha borrado ningun alumno");
    }
}