import java.util.ArrayList;

public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();
    private Jugador jugador1;
    private Jugador jugador2;

    public void play (){
        CONSOLA.welcomeMessage();

        jugador1 = new Jugador(CONSOLA.askPlayerName());
        jugador2 = new Jugador(CONSOLA.askPlayerName());
        Jugador jugadorActual = jugador1;
        Jugador jugadorRival = jugador2;

        ArrayList<Barco> barcos = displayBoats();

        for(int i = 0; i <= 1; i++){
            askWhereToPutBoat(barcos, jugadorActual, 0);
            Jugador temp = jugadorActual;
            jugadorActual = jugadorRival;
            jugadorRival = temp;
        }
    }

    private ArrayList<Barco> displayBoats(){
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

    private void askWhereToPutBoat(ArrayList<Barco> barcos, Jugador jugadorActual, int indexBarco){
        for (int i = indexBarco; i < barcos.size(); i++) {
            int posicionLinea = CONSOLA.askPositionLineBoat(jugadorActual, barcos.get(i));
            int posicionColumna = CONSOLA.askPositionColumnBoat(jugadorActual, barcos.get(i));
            int direccion = CONSOLA.askDirectionPutBoat();
            putBoatOnBoard(jugadorActual, barcos, barcos.indexOf(barcos.get(i)), posicionLinea, posicionColumna, direccion);
            CONSOLA.printBoard(jugadorActual.getTableroVisible());
        }
    }

    private void putBoatOnBoard(Jugador jugadorActual, ArrayList<Barco> barcos, int indexBarco, int posicionLinea, int posicionColumna, int direccion){
        Barco barco = barcos.get(indexBarco);
        Celda[][] tablero = jugadorActual.getTableroVisible();

        if(tablero[posicionLinea][posicionColumna].getEsBarco()){
            CONSOLA.theresAlreadyABoatInPosition();
            askWhereToPutBoat(barcos, jugadorActual, indexBarco);
            return;
        }

        for(int i = 0; i < barco.getLongitud(); i++){
            if(posicionLinea > tablero.length - 1 || posicionColumna > tablero[0].length - 1){
                if(direccion == 1){
                    for(int j = i; j > 0; j--){
                        tablero[posicionLinea - 1][posicionColumna].setEsBarco(false);
                        tablero[posicionLinea - 1][posicionColumna].setNoEsBarco(true);
                    }
                } else {
                    for(int j = i; j > 0; j--){
                        tablero[posicionLinea][posicionColumna - 1].setEsBarco(false);
                        tablero[posicionLinea][posicionColumna - 1].setNoEsBarco(true);
                    }
                }

                CONSOLA.boatIndexOutOfBounds();
                askWhereToPutBoat(barcos, jugadorActual, indexBarco);
                return;
            }

            tablero[posicionLinea][posicionColumna].setEsBarco(true);
            tablero[posicionLinea][posicionColumna].setNoEsBarco(false);

            if(direccion == 1 && posicionLinea <= tablero.length - 1){
                posicionLinea++;
            } else if(direccion == 2 && posicionColumna <= tablero[0].length - 1){
                posicionColumna++;
            }
        }
    }
}
