package com.andremapa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            String[][] board = {{" = ", " = ", " = "},
                                {" = ", " = ", " = "},
                                {" = ", " = ", " = "}};
            printBoard(board);
            for (; ;) {
                playerMovie(board, sc, " X ");
                if (verifyIfExistsAWinnerCondition(board, " X ")) {
                    System.out.println("Player X is the winner!");
                    break;
                }

                playerMovie(board, sc, " O ");
                if (verifyIfExistsAWinnerCondition(board, " O ")) {
                    System.out.println("Player O is the winner! ");
                    break;
                }
            }
            clearBufferScanner(sc);
            System.out.print("Play again? ('S'/'N') ");
            var ref = sc.nextLine().toUpperCase();
            if (ref.equals("N")){
                playAgain = false;
            }
        }
    }


    public static void clearBufferScanner(Scanner sc){
        if(sc.hasNextLine()){
            sc.nextLine();
        }
    }

    public static void printBoard(String[][] board){
        for (String[] strings : board) {
            System.out.println(" ");
            for (String string : strings) {
                System.out.print(string);
            }
        }
        System.out.println("\n");
    }

    public static void playerMovie(String[][] board, Scanner sc, String player) {
        int control = 0;
        while (control == 0) {
            System.out.print("Player" + player + "make your movie, enter column and row: ");
            int col = sc.nextInt();
            int line = sc.nextInt();
            if (verifyIfThePlayerCanMakeTheMovement(board, player, col, line)) {
                board[line][col] = player;
                control++;
            }
            printBoard(board);
        }
    }

    public static boolean verifyIfThePlayerCanMakeTheMovement(String[][] board, String player, int col, int line){
        if(player.equals(" X ")){
            return board[line][col].equals(" = ");
        }
        if (player.equals(" O ")){
            return board[line][col].equals(" = ");
        }
        return false;
    }

    public static boolean verifyIfExistsAWinnerCondition(String[][] board, String player){
        boolean[][] endGame = scannerBoard(board, player);
        return testIfHaveAWinnerCondition(
                endGame[0][0] && endGame[1][0] && endGame[2][0]
                        || endGame[0][1] && endGame[1][1] && endGame[2][1]
                        || endGame[0][2] && endGame[1][2] && endGame[2][2]
                        || endGame[0][0] && endGame[0][1] && endGame[0][2]
                        || endGame[1][0] && endGame[1][1] && endGame[1][2]
                        || endGame[2][0] && endGame[2][1] && endGame[2][2]
                        || endGame[0][0] && endGame[1][1] && endGame[2][2]
                        || endGame[2][0] && endGame[1][1] && endGame[0][2]);
    }

    public static boolean[][] scannerBoard(String[][] board, String player){
        boolean[][] scanBoard = new boolean [3][3];
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                scanBoard[i][j] = board[i][j].contains(player);
            }
        }
        return scanBoard;
    }

    private static boolean testIfHaveAWinnerCondition(boolean b) {
        return b;
    }
}
