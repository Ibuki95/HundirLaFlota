public class Celda {
    private boolean celdaTapada;
    private boolean noEsBarco;
    private ParteBarco parteBarco;

    public Celda() {
        this.celdaTapada = false;
        this.noEsBarco = true;
        this.parteBarco = null;
    }

    public void setCeldaTapada(boolean celdaTapada) {
        this.celdaTapada = celdaTapada;
    }

    public void setNoEsBarco(boolean noEsBarco) {
        this.noEsBarco = noEsBarco;
    }

    public void setParteBarco(ParteBarco parteBarco){
        this.parteBarco = parteBarco;
    }
    public ParteBarco getParteBarco(){
        return parteBarco;
    }

    public boolean getEsBarco() {
        return this.parteBarco != null;
    }

    public void uncoverCell(){
        this.celdaTapada = false;
        if(getEsBarco()){
            this.parteBarco.attackBoat();
            return;
        }
        this.noEsBarco = true;
    }

    @Override
    public String toString() {
        if (this.celdaTapada) {
            return "*";
        }

        if (this.noEsBarco) {
            return "-";
        }

        if (this.getEsBarco()) {
            if(this.parteBarco.isBarcoHerido()){
                return "X";
            }
            return "B";
        }

        return "";
    }
}
