public class Barco {
    private final int LONGITUD;
    private final String NOMBRE;
    private final ParteBarco[] PARTES;

    public Barco (TipoBarco tipo){
        LONGITUD = tipo.getLongitud();
        NOMBRE = tipo.name();
        this.PARTES = new ParteBarco[LONGITUD];
        for(int i = 0; i < PARTES.length; i++){
           PARTES[i] = new ParteBarco(this);
        }
    }

    public int getLongitud() {
        return LONGITUD;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public ParteBarco[] getPartes(){
        return PARTES;
    }

    public boolean checkIfBoatSank(){
        for (ParteBarco parte : PARTES){
            if(!parte.isBarcoHerido()){
                return false;
            }
        }

        return true;
    }
}
