package ppss;

public class Factoria {
    private static IOperacionBO io = null;

    public static IOperacionBO create() {
        if (io != null) {
            return io;
        }
        else {
            return new Operacion();
        }
    }

    static void setIOperacionBO (IOperacionBO operacion) {
        io = operacion;
    }
}
