package ppss;

import java.time.LocalDate;

/*NO podemos refactirar de estas formas:
*   - No podemos añadir atributos de clase
*   - No podemos añadir parámetros a la SUT
*   - No podemos usar una clase Factoría
*   SOLUCIÓN: Factoría local para crear el objeto IService
*       y setter para añadir una implementación alternativa del objeto Calendario
*       CORRECCIÓN: el setter no es necesario porque el atributo es protected*/

public class AlquilaCoches {
    protected Calendario calendario = new Calendario();

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public IService getServicio() {
        IService servicio = new Servicio();
        return servicio;
    }

    public Ticket calculaPrecio(TipoCoche tipo, LocalDate inicio, int ndias)
            throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia,precioTotal =0.0f;
        float porcentaje = 0.25f;
        String observaciones = "";
        IService servicio = getServicio();
        precioDia = servicio.consultaPrecio(tipo); // consultaPrecio() es un DOC
        for (int i=0; i<ndias;i++) {
            LocalDate otroDia = inicio.plusDays((long)i);
            try {
                if (calendario.es_festivo(otroDia)) { // es_festivo() es un DOC
                    precioTotal += (1+ porcentaje)*precioDia;
                } else {
                    precioTotal += (1- porcentaje)*precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: "+otroDia+"; ";
            }
        }
        if (observaciones.length()>0) {
            throw new MensajeException(observaciones);
        }
        ticket.setPrecio_final(precioTotal);
        return ticket;
    }
}