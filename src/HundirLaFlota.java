import java.util.ArrayList;

public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();

    public void play() {
        int turno = 0;

        CONSOLA.welcomeMessage();

        Tablero[] tableros = {new Tablero(CONSOLA.askPlayerName()), new Tablero(CONSOLA.askPlayerName())};

        while (true){
            Tablero jugadorActual = tableros[turno % 2];
            Tablero jugadorRival = tableros[(turno + 1) % 2];

            CONSOLA.printBoard(jugadorRival.getTablero());

            if(attackBoats(jugadorActual, jugadorRival)){
                CONSOLA.wonGame(jugadorActual, jugadorRival);
                return;
            }

            turno++;
        }
    }

    private boolean attackBoats(Tablero jugadorActual, Tablero jugadorRival){
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
