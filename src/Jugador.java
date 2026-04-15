import java.util.ArrayList;

public class Jugador {
    private final Celda[][] TABLERO;
    private final String NOMBRE;

    public Jugador(String NOMBRE){
        TABLERO = new Celda[8][8];
        fillInitialBoard();
        this.NOMBRE = NOMBRE;
    }

    private void fillInitialBoard(){
        for(Celda[] celdas : TABLERO){
            for(int j = 0; j < TABLERO[0].length; j++){
                celdas[j] = new Celda();
            }
        }
    }

    public void coverUpBoard(){
        for (Celda[] celdas : TABLERO) {
            for (int j = 0; j < TABLERO[0].length; j++) {
                celdas[j].setCeldaTapada(true);
            }
        }
    }

    public Celda[][] getTablero() {
        return TABLERO;
    }

    public String getNombre() {
        return NOMBRE;
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
