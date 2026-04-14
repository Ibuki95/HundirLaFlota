import java.util.ArrayList;

public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();
    private Jugador jugador1;
    private Jugador jugador2;

    public void play() {
        CONSOLA.welcomeMessage();

        jugador1 = new Jugador(CONSOLA.askPlayerName());
        jugador2 = new Jugador(CONSOLA.askPlayerName());
        Jugador jugadorActual = jugador1;
        Jugador jugadorRival = jugador2;

        CONSOLA.printBoard(jugadorActual.getTableroVisible());

        for (int i = 0; i <= 1; i++) {
            askWhereToPutBoat(jugadorActual.displayBoats(), jugadorActual, 0);
            Jugador temp = jugadorActual;
            jugadorActual = jugadorRival;
            jugadorRival = temp;
        }

    }

    private void askWhereToPutBoat(ArrayList<Barco> barcos, Jugador jugadorActual, int indexBarco) {
        for (int i = indexBarco; i < barcos.size(); i++) {
            int posicionLinea = CONSOLA.askPositionLineBoat(jugadorActual, barcos.get(i));
            int posicionColumna = CONSOLA.askPositionColumnBoat(jugadorActual, barcos.get(i));
            int direccion = CONSOLA.askDirectionPutBoat();
            putBoatOnBoard(jugadorActual, barcos, barcos.indexOf(barcos.get(i)), posicionLinea, posicionColumna, direccion);
            CONSOLA.printBoard(jugadorActual.getTableroVisible());
        }
    }

    private void putBoatOnBoard(Jugador jugadorActual, ArrayList<Barco> barcos, int indexBarco, int posicionLinea, int posicionColumna, int direccion) {
        Barco barco = barcos.get(indexBarco);
        Celda[][] tablero = jugadorActual.getTableroVisible();
        ParteBarco[] partes = barco.getPartes();

        if (tablero[posicionLinea][posicionColumna].getEsBarco()) {
            CONSOLA.thereIsAlreadyABoatInPosition(tablero);
            askWhereToPutBoat(barcos, jugadorActual, indexBarco);
            return;
        }

        for (int i = 0; i < barco.getLongitud(); i++) {
            if (checkIfAnyBoatsAreAroundPosition(tablero, posicionLinea, posicionColumna)) {
                CONSOLA.thereAreBoatsAround(tablero);
                askWhereToPutBoat(barcos, jugadorActual, indexBarco);
                return;
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
                askWhereToPutBoat(barcos, jugadorActual, indexBarco);
                return;
            }

            for(int j = 0; j < barco.getLongitud(); j++){
                tablero[posicionLinea][posicionColumna].setParteBarco(partes[j]);
                tablero[posicionLinea][posicionColumna].setNoEsBarco(false);
            }

            if (direccion == 1 && posicionLinea <= tablero.length - 1) {
                posicionLinea++;
            } else if (direccion == 2 && posicionColumna <= tablero[0].length - 1) {
                posicionColumna++;
            }
        }
    }

    private boolean checkIfAnyBoatsAreAroundPosition(Celda[][] tablero, int posicionLinea, int posicionColumna) {
        int[][] direcciones = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                for (int[] direccion : direcciones) {
                    int lineaDireccion = direccion[0];
                    int columnaDireccion = direccion[1];
                    int lineaComprobarSiHayBarco = posicionLinea + lineaDireccion;
                    int columnaComprobarSiHayBarco = posicionColumna + columnaDireccion;

                    if ((lineaComprobarSiHayBarco < tablero.length && lineaComprobarSiHayBarco >= 0) &&
                            (columnaComprobarSiHayBarco < tablero[0].length && columnaComprobarSiHayBarco >= 0) &&
                            (tablero[lineaComprobarSiHayBarco][columnaComprobarSiHayBarco].getEsBarco()) &&
                            (tablero[lineaComprobarSiHayBarco][columnaComprobarSiHayBarco].getParteBarco().getBarco() != tablero[posicionLinea][posicionColumna].getParteBarco().getBarco())) {
                        return true;
                    }
                }

            }
        }

        return false;
    }
}
