import java.util.ArrayList;

public class Tablero {
    private final Celda[][] TABLERO;
    private final Jugador jugador;
    private final Consola CONSOLA = new Consola();

    public Tablero(String nombreJugador){
        TABLERO = new Celda[8][8];
        fillInitialBoard();
        jugador = new Jugador(nombreJugador);
        prepareBoard();
        coverUpBoard();
    }

    private void fillInitialBoard(){
        for(Celda[] celdas : TABLERO){
            for(int j = 0; j < TABLERO[0].length; j++){
                celdas[j] = new Celda();
            }
        }
    }

    public Celda[][] getTablero() {
        return TABLERO;
    }

    public String getJugador(){
        return jugador.getNombre();
    }

    public void prepareBoard(){
        askWhereToPutBoat(displayBoats(), jugador);
    }

    public void coverUpBoard(){
        for (Celda[] celdas : TABLERO) {
            for (int j = 0; j < TABLERO[0].length; j++) {
                celdas[j].setCeldaTapada(true);
            }
        }
    }

    private void askWhereToPutBoat(ArrayList<Barco> barcos, Jugador jugadorActual) {
        for (int i = 0; i < barcos.size(); i++) {
            int posicionLinea = CONSOLA.askPositionLineBoat(jugadorActual, barcos.get(i));
            int posicionColumna = CONSOLA.askPositionColumnBoat(jugadorActual, barcos.get(i));
            int direccion = CONSOLA.askDirectionPutBoat();
            if (putBoatOnBoard(barcos, barcos.indexOf(barcos.get(i)), posicionLinea, posicionColumna, direccion)) {
                CONSOLA.printBoard(getTablero());
            } else {
                i--;
            }
        }
    }

    private ArrayList<Barco> displayBoats() {
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

    private boolean putBoatOnBoard(ArrayList<Barco> barcos, int indexBarco, int posicionLinea, int posicionColumna, int direccion) {
        Barco barco = barcos.get(indexBarco);
        Celda[][] tablero = getTablero();
        ParteBarco[] partes = barco.getPartes();

        if (tablero[posicionLinea][posicionColumna].getEsBarco()) {
            CONSOLA.thereIsAlreadyABoatInPosition(tablero);
            return false;
        }

        for (int i = 0; i < barco.getLongitud(); i++) {
            if (checkIfAnyBoatsAreAroundPosition(tablero, posicionLinea, posicionColumna, partes[0])) {
                CONSOLA.thereAreBoatsAround(tablero);
                return false;
            }

            if (posicionLinea > tablero.length - 1 || posicionColumna > tablero[0].length - 1) {
                for (int j = i; j > 0; j--) {
                    if (direccion == 1) {
                        tablero[posicionLinea - 1][posicionColumna].setParteBarco(null);
                        tablero[posicionLinea - 1][posicionColumna].setNoEsBarco(true);
                    } else {
                        tablero[posicionLinea][posicionColumna - 1].setParteBarco(null);
                        tablero[posicionLinea][posicionColumna - 1].setNoEsBarco(true);
                    }
                }

                CONSOLA.boatIndexOutOfBounds(tablero);
                return false;
            }

            tablero[posicionLinea][posicionColumna].setParteBarco(partes[i]);
            tablero[posicionLinea][posicionColumna].setNoEsBarco(false);

            if (direccion == 1 && posicionLinea <= tablero.length - 1) {
                posicionLinea++;
            } else if (direccion == 2 && posicionColumna <= tablero[0].length - 1) {
                posicionColumna++;
            }
        }

        return true;
    }

    private boolean checkIfAnyBoatsAreAroundPosition(Celda[][] tablero, int posicionLinea, int posicionColumna, ParteBarco parte) {
        int[][] direcciones = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        for (int[] direccion : direcciones) {
            int lineaDireccion = direccion[0];
            int columnaDireccion = direccion[1];
            int lineaComprobarSiHayBarco = posicionLinea + lineaDireccion;
            int columnaComprobarSiHayBarco = posicionColumna + columnaDireccion;

            if ((lineaComprobarSiHayBarco < tablero.length && lineaComprobarSiHayBarco >= 0) &&
                    (columnaComprobarSiHayBarco < tablero[0].length && columnaComprobarSiHayBarco >= 0) &&
                    (tablero[lineaComprobarSiHayBarco][columnaComprobarSiHayBarco].getEsBarco()) &&
                    (tablero[lineaComprobarSiHayBarco][columnaComprobarSiHayBarco].getParteBarco().getBarco() != parte.getBarco())) {
                return true;
            }
        }

        return false;
    }
}
