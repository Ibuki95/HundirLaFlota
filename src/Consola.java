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

    public void printBoard(Celda[][] tablero) {
        System.out.print("  ");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int askPositionLineBoat(Jugador jugador, Barco barco){
        System.out.println(jugador.getNombreJugador() + ", elija en que linea va a poner un/a " + barco.getNombre().toLowerCase() + ". Debe estar situado/a una casilla lejos de otros barcos.");
        return checkIfPositionLineIsCorrect(SC.nextInt());
    }

    private int checkIfPositionLineIsCorrect(int posicionLinea){
        while (true) {
            if (posicionLinea < 0 || posicionLinea > 7) {
                System.out.println("¡Número de linea incorrecto! Vuelva a intentarlo.");
                posicionLinea = SC.nextInt();
            } else {
                return posicionLinea;
            }
        }
    }

    public int askPositionColumnBoat(Jugador jugador, Barco barco){
        System.out.println(jugador.getNombreJugador() + ", elija en que columna va a poner un/a " + barco.getNombre().toLowerCase() + ". Debe estar situado/a una casilla lejos de otros barcos.");
        return checkIfPositionColumnIsCorrect(SC.nextInt());
    }

    private int checkIfPositionColumnIsCorrect(int posicionColumna){
        while (true) {
            if (posicionColumna < 0 || posicionColumna > 7) {
                System.out.println("¡Número de columna incorrecto! Vuelva a intentarlo.");
                posicionColumna = SC.nextInt();
            } else {
                return posicionColumna;
            }
        }
    }

    public int askDirectionPutBoat(){
        System.out.println("¿En que dirección va a situar el barco?\n 1 - Vertical\n 2 - Horizontal");
        return checkDirectionForBoat(SC.nextInt());
    }

    private int checkDirectionForBoat(int direccion) {
        while (true) {
            switch (direccion) {
                case 1:
                case 2:
                    return direccion;
                default:
                    System.out.println("¡Dirección incorrecta! Vuelva a intentarlo.");
                    direccion = SC.nextInt();
            }
        }
    }

    public void boatIndexOutOfBounds(Celda[][] tablero){
        System.out.println("¡No puede poner su barco aquí! ¡Se sale del tablero! Vuelva a intentarlo.");
        printBoard(tablero);
    }

    public void thereIsAlreadyABoatInPosition(Celda[][] tablero){
        System.out.println("¡Ya hay un barco aquí! vuelva a intentarlo.");
        printBoard(tablero);
    }

    public void thereAreBoatsAround(Celda[][] tablero){
        System.out.println("¡No puede posicionar su barco aquí! Necesita estar alejado mínimo una celda de los demás barcos.");
        printBoard(tablero);
    }
}
