public class Barco {
    private final int LONGITUD;
    private final String NOMBRE;

    public Barco (TipoBarco tipo){
        LONGITUD = tipo.getLongitud();
        NOMBRE = tipo.name();
    }

    public int getLongitud() {
        return LONGITUD;
    }

    public String getNombre() {
        return NOMBRE;
    }
}
