package Game;

import java.util.Random;

public class UI {
    private static Spielfeld spielfeld;
    private static int[][] board;
    public static final String YELLOW = "\u001B[43m";
    public static final String RED = "\u001B[41m";
    public static final String GREEN = "\u001B[42m";
    public static final String BLUE = "\u001B[44m";
    public static final String PURPLE = "\u001B[45m";
    public static final String WHITE = "\u001B[47m";
    public static final String CYAN = "\u001B[46m";
    public static final String RESET = "\u001B[0m";
    public static void main(String[] args) {
        spielfeld = new Spielfeld(9,7);
        board = spielfeld.getSpielfeld();
        printBoard();
    }



    
    public static void printBoard(){
        System.out.println("Board:");
        System.out.print("   ");
        for(int a = 0; a < board.length; a++){
            System.out.print( a + "  ");
        }
        System.out.println("");
        for(int i = 0; i < board.length; i++){
            System.out.print(i + " ");
            for(int m = 0; m < board.length; m++){
                switch(board[i][m]){
                    case 0:
                        System.out.print(YELLOW + "   " + RESET);
                        break;
                    case 1:
                        System.out.print(RED + "   " + RESET);
                        break;
                    case 2:
                        System.out.print(GREEN + "   " + RESET);
                        break;
                    case 3:
                        System.out.print(BLUE + "   " + RESET);
                        break;
                    case 4:
                        System.out.print(PURPLE + "   " + RESET);
                        break;
                    case 5:
                        System.out.print(WHITE + "   " + RESET);
                        break;
                    case 6:
                        System.out.print(CYAN + "   " + RESET);
                        break;

                    default:
                        System.out.print("    ");
                        break;
                }
            }
            System.out.println("");
        }
    }
}
