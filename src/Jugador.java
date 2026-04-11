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
}
