public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();
    private Jugador jugador1;
    private Jugador jugador2;

    public void play (){
        CONSOLA.welcomeMessage();

        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }
}
