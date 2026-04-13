public class Barco {
    private final int LONGITUD;
    private final String NOMBRE;
    private ParteBarco[] partes;

    public Barco (TipoBarco tipo){
        LONGITUD = tipo.getLongitud();
        NOMBRE = tipo.name();
        this.partes = new ParteBarco[LONGITUD];
        for(int i = 0; i < partes.length; i++){
           partes[i] = new ParteBarco(this);
        }
    }

    public int getLongitud() {
        return LONGITUD;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public ParteBarco[] getPartes(){
        return partes;
    }
}
