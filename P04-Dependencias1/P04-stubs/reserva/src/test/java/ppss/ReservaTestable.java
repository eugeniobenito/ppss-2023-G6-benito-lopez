package ppss;

import java.util.Map;

public class ReservaTestable extends Reserva {
    private Map<String, String> credencialesCorrectas;

    public void setCredencialesCorrectas(Map<String, String> credencialesCorrectas) {
        this.credencialesCorrectas = credencialesCorrectas;
    }

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsuario) {
        String passwordCorrecta = credencialesCorrectas.get(login);
        return passwordCorrecta != null && passwordCorrecta.equals(password);
    }
}
