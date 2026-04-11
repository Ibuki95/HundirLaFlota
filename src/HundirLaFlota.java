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

        Barco[] barcos = displayBoats();

        while (true){
            for (Barco barco : barcos) {
                CONSOLA.askPositionLineBoat(jugadorActual, barco);
                CONSOLA.askPositionColumnBoat(jugadorActual, barco);
                CONSOLA.printBoard(jugadorActual.getTableroVisible());
            }
            break;
        }

    }

    private Barco[] displayBoats(){
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

        return new Barco[]{portaviones, buque1, buque2, crucero1, crucero2, crucero3, lancha1, lancha2, lancha3, lancha4};
    }
}
