import java.util.Scanner;

public class Consola {
    private final Scanner SC = new Scanner(System.in);

    public void welcomeMessage(){
        System.out.println("¡Bienvenidos a hundir la flota! ¡Que empiece el juego!");
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
}
