package ppss.ejercicio1;

public class GestorLlamadasTestable extends GestorLlamadas {
    int resultado;

    @Override
    public int getHoraActual() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
