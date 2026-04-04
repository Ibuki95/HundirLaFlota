public enum TipoBarco {
    PORTAVIONES(4),
    BUQUE(3),
    CRUCERO(2),
    LANCHA(1);

    private final int LONGITUD;

    private TipoBarco(int longitud){
        this.LONGITUD = longitud;
    }

    public int getLongitud() {
        return LONGITUD;
    }
}
