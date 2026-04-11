import java.util.Scanner;

public class Consola {
    private final Scanner SC = new Scanner(System.in);

    public void welcomeMessage(){
        System.out.println("¡Bienvenidos a hundir la flota!\nAmbos jugadores van a disponer de 10 barcos, un Portaviones, dos Buques, tres Cruceros y cuatro Lanchas.\n¡Que empiece el juego!");
    }

    public String askPlayerName(){
        System.out.print("Elija un nombre de jugador: ");
        return SC.nextLine();
    }

    public void printBoard(Celda[][] tablero){
        for (Celda[] cells : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(cells[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void askPositionLineBoat(Jugador jugador, Barco barco){
        System.out.println(jugador.getNombreJugador() + ", elija en que linea va a poner un/a " + barco.getNombre().toLowerCase());
        int posicionLinea = checkIfPositionLineIsCorrect(SC.nextInt());
    }

    private int checkIfPositionLineIsCorrect(int posicionLinea){
        while (true) {
            if (posicionLinea < 0 || posicionLinea > 7) {
                System.out.println("Número de linea incorrecto! Vuelva a intentarlo!");
                posicionLinea = SC.nextInt();
            } else {
                return posicionLinea;
            }
        }
    }

    public void askPositionColumnBoat(Jugador jugador, Barco barco){
        System.out.println(jugador.getNombreJugador() + ", elija en que columna va a poner un/a " + barco.getNombre().toLowerCase());
        int posicionColumna = checkIfPositionColumnIsCorrect(SC.nextInt());
    }

    private int checkIfPositionColumnIsCorrect(int posicionColumna){
        while (true) {
            if (posicionColumna < 0 || posicionColumna > 7) {
                System.out.println("Número de columna incorrecto! Vuelva a intentarlo!");
                posicionColumna = SC.nextInt();
            } else {
                return posicionColumna;
            }
        }
    }
}
