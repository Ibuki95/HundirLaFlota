public class HundirLaFlota {
    private final Consola CONSOLA = new Consola();
    private Celda[][] tablero = new Celda[8][8];

    public void play (){
        CONSOLA.welcomeMessage();

        fillInitialBoard();
    }

    private void fillInitialBoard(){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                tablero[i][j] = new Celda();
            }
        }
    }
}
