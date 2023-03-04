package ppss.ejercicio2;

public class CalendarioStub extends Calendario{
    private int resultado;

    public CalendarioStub(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public int getHoraActual() {
        return resultado;
    }
}
