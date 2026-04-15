import java.util.ArrayList;

public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();

    public void play() {
        CONSOLA.welcomeMessage();

        Jugador jugadorActual = new Jugador(CONSOLA.askPlayerName());
        Jugador jugadorRival = new Jugador(CONSOLA.askPlayerName());

        for (int i = 0; i <= 1; i++) {
            CONSOLA.printBoard(jugadorActual.getTablero());
            askWhereToPutBoat(jugadorActual.displayBoats(), jugadorActual);
            jugadorActual.coverUpBoard();

            Jugador temp = jugadorActual;
            jugadorActual = jugadorRival;
            jugadorRival = temp;
        }

        while (true){
            CONSOLA.printBoard(jugadorRival.getTablero());

            if(attackBoats(jugadorActual, jugadorRival)){
                CONSOLA.wonGame(jugadorActual, jugadorRival);
                return;
            }

            Jugador temp = jugadorActual;
            jugadorActual = jugadorRival;
            jugadorRival = temp;
        }
    }

    private void askWhereToPutBoat(ArrayList<Barco> barcos, Jugador jugadorActual) {
        for (int i = 0; i < barcos.size(); i++) {
            int posicionLinea = CONSOLA.askPositionLineBoat(jugadorActual, barcos.get(i));
            int posicionColumna = CONSOLA.askPositionColumnBoat(jugadorActual, barcos.get(i));
            int direccion = CONSOLA.askDirectionPutBoat();
            if (putBoatOnBoard(jugadorActual, barcos, barcos.indexOf(barcos.get(i)), posicionLinea, posicionColumna, direccion)) {
                CONSOLA.printBoard(jugadorActual.getTablero());
            } else {
                i--;
            }
        }
    }

    private boolean putBoatOnBoard(Jugador jugadorActual, ArrayList<Barco> barcos, int indexBarco, int posicionLinea, int posicionColumna, int direccion) {
        Barco barco = barcos.get(indexBarco);
        Celda[][] tablero = jugadorActual.getTablero();
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

    private boolean attackBoats(Jugador jugadorActual, Jugador jugadorRival){
        Celda [][] tablero = jugadorRival.getTablero();

        while(true) {
            int posicionLinea = CONSOLA.askPositionLineAttack(jugadorActual);
            int posicionColumna = CONSOLA.askPositionColumnAttack(jugadorActual);

            if(!tablero[posicionLinea][posicionColumna].isCeldaTapada()){
                CONSOLA.cellAlreadyUncovered();
                continue;
            }

            if (tablero[posicionLinea][posicionColumna].getEsBarco()) {
                ParteBarco parte = tablero[posicionLinea][posicionColumna].getParteBarco();

                tablero[posicionLinea][posicionColumna].uncoverCell();
                CONSOLA.boatHit(jugadorActual);

                if (parte.getBarco().checkIfBoatSank()) {
                    CONSOLA.boatSink(parte.getBarco(), jugadorActual, jugadorRival);
                }

                CONSOLA.printBoard(tablero);

                if(checkIfWonGame(jugadorRival.getTablero())){
                    return true;
                }

            } else {
                tablero[posicionLinea][posicionColumna].uncoverCell();
                CONSOLA.noBoatAttacked();
                return false;
            }
        }
    }

    private boolean checkIfWonGame(Celda[][] tablero){
        for (Celda[] celdas : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (celdas[j].getEsBarco()) {
                    if (!celdas[j].getParteBarco().getBarco().checkIfBoatSank()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
