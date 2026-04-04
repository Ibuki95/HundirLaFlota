public class Barco {
    private final int LONGITUD;

    public Barco (TipoBarco tipo){
        LONGITUD = tipo.getLongitud();
    }

    public int getLongitud() {
        return LONGITUD;
    }
}
