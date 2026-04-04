public class Celda {
    private boolean celdaTapada;
    private boolean esBarco;
    private boolean noEsBarco;
    private boolean barcoHerido;

    public Celda() {
        this.celdaTapada = true;
        this.esBarco = false;
        this.noEsBarco = false;
        this.barcoHerido = false;
    }

    public void setCeldaTapada(boolean celdaTapada) {
        this.celdaTapada = celdaTapada;
    }
    public boolean getCeldaTapada() {
        return celdaTapada;
    }

    public void setEsBarco(boolean esBarco) {
        this.esBarco = esBarco;
    }
    public boolean getEsBarco() {
        return esBarco;
    }

    public void setNoEsBarco(boolean noEsBarco) {
        this.noEsBarco = noEsBarco;
    }
    public boolean getNoEsBarco() {
        return noEsBarco;
    }

    public void setBarcoHerido(boolean barcoHerido) {
        this.barcoHerido = barcoHerido;
    }
    public boolean getBarcoHerido() {
        return barcoHerido;
    }

    @Override
    public String toString() {
        if (this.celdaTapada) {
            return "*";
        }

        if (this.noEsBarco) {
            return "0";
        }

        if (this.esBarco) {
            return "B";
        }

        if (this.barcoHerido) {
            return "X";
        }

        return "";
    }
}
