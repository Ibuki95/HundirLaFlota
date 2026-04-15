public class ParteBarco {

    private final Barco BARCO;
    private boolean barcoHerido;

    public ParteBarco(Barco barco){
        this.BARCO = barco;
        this.barcoHerido = false;
    }

    public Barco getBarco(){
        return BARCO;
    }

    public void attackBoat() {
        this.barcoHerido = true;
    }

    public boolean isBarcoHerido() {
        return barcoHerido;
    }
}
