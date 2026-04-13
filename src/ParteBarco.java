public class ParteBarco {

    private Barco barco;
    private boolean barcoHerido;

    public ParteBarco(Barco barco){
        this.barco = barco;
        this.barcoHerido = false;
    }

    public Barco getBarco(){
        return barco;
    }

    public void setBarcoHerido(boolean barcoHerido) {
        this.barcoHerido = barcoHerido;
    }

    public boolean isBarcoHerido() {
        return barcoHerido;
    }
}
