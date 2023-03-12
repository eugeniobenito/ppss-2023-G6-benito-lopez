package ppss;

import java.time.LocalDate;

public class CalendarioStub extends Calendario {
    int[] diasFestivos;
    int[] diasConExcepcion;

    public CalendarioStub(int[] festivos, int[] conExcepcion) {
        this.diasFestivos = festivos;
        this.diasConExcepcion = conExcepcion;
    }

    @Override
    public boolean es_festivo(LocalDate dia) {
        int diaInt = dia.getDayOfMonth();

        // TODO: Aquí deberíamos ahorrarnos los bucles, debemos usar ArrayList
        // Verificar si el día está en el array de días con excepción
        for (int fechaExcepcion : diasConExcepcion) {
            if (diaInt == fechaExcepcion) {
                throw new CalendarioException("Error en dia: " + diaInt);
            }
        }

        // Verificar si el día está en el array de días festivos
        for (int fechaFestivo : diasFestivos) {
            if (diaInt == fechaFestivo) {
                return true;
            }
        }
        return false;
    }
}
