import java.util.ArrayList;

public class Jugador {
    private Celda[][] tableroVisible;
    private Celda[][] tableroTapado;
    private String nombreJugador;

    public Jugador(String nombreJugador){
        tableroVisible = new Celda[8][8];
        fillInitialBoard(tableroVisible, true);
        tableroTapado = new Celda[8][8];
        fillInitialBoard(tableroTapado, false);
        this.nombreJugador = nombreJugador;
    }

    private void fillInitialBoard(Celda[][] tablero, boolean esVisible){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                tablero[i][j] = new Celda();
                if(esVisible){
                    tablero[i][j].setCeldaTapada(false);
                    tablero[i][j].setNoEsBarco(true);
                }
            }
        }
    }

    public Celda[][] getTableroVisible() {
        return tableroVisible;
    }

    public Celda[][] getTableroTapado() {
        return tableroTapado;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public ArrayList<Barco> displayBoats() {
        Barco portaviones = new Barco(TipoBarco.PORTAVIONES);
        Barco buque1 = new Barco(TipoBarco.BUQUE);
        Barco buque2 = new Barco(TipoBarco.BUQUE);
        Barco crucero1 = new Barco(TipoBarco.CRUCERO);
        Barco crucero2 = new Barco(TipoBarco.CRUCERO);
        Barco crucero3 = new Barco(TipoBarco.CRUCERO);
        Barco lancha1 = new Barco(TipoBarco.LANCHA);
        Barco lancha2 = new Barco(TipoBarco.LANCHA);
        Barco lancha3 = new Barco(TipoBarco.LANCHA);
        Barco lancha4 = new Barco(TipoBarco.LANCHA);

        ArrayList<Barco> barcos = new ArrayList<Barco>();
        barcos.add(portaviones);
        barcos.add(buque1);
        barcos.add(buque2);
        barcos.add(crucero1);
        barcos.add(crucero2);
        barcos.add(crucero3);
        barcos.add(lancha1);
        barcos.add(lancha2);
        barcos.add(lancha3);
        barcos.add(lancha4);

        return barcos;
    }
}
