package ppss;

public class ServicioSTUB implements IService {
    float precio;

    public ServicioSTUB(float precio) {
        this.precio = precio;
    }

    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return precio;
    }
}
