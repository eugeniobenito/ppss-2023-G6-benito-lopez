package ppss.ejercicio2;

public class GestorLlamadasTestable extends GestorLlamadas {
    Calendario calendario;

    @Override
    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario c) {
        this.calendario = c;
    }
}
