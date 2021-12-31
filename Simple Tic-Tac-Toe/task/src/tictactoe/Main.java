package tictactoe;

import java.awt.*;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    final static int size = 3;

    public static void main(String[] args) {
        // write your code here
        // System.out.print("Enter cells: ");
        char[] gameState = "_________".toCharArray();
        Game game = new Game(gameState);
        game.printGame();
        while (true) {
            game.getAction();
            game.printGame();
            if (game.isWinner('X') || game.isWinner('O') || !game.notFilled()) {
                break;
            }
        }
        game.printResult();
    }

    public static class Game {
        private char[] gameState;

        public Game(char[] gameState) {
            this.gameState = gameState;
        }

        public void printGame() {
            System.out.println("---------");
            for (int i = 0; i < size; i++) {
                System.out.print("| ");
                for (int j = 0; j < size; j++) {
                    System.out.print(gameState[i * 3 + j] + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");
        }

        public void printResult() {
            if ((isWinner('X') && isWinner('O'))|| unfair()) {
                System.out.println("Impossible");
            } else if (isWinner('X')) {
                System.out.println("X wins");
            } else if (isWinner('O')) {
                System.out.println("O wins");
            } else if (notFilled()) {
                System.out.println("Game not finished");
            } else {
                System.out.println("Draw");
            }
        }

        public void getAction() {
            System.out.print("Enter the coordinates: ");
            String[] input = scanner.nextLine().split("\\s+");
            if (input.length < 2) {
                System.out.print("You should enter two numbers!");
                getAction();
            } else if (!isInteger(input[0]) || !isInteger(input[1])) {
                System.out.println("You should enter numbers!");
                getAction();
            } else {
                int row = Integer.parseInt(input[0]) - 1;
                int col = Integer.parseInt(input[1]) - 1;
                if (row < 0 || 2 < row || col < 0 || 2 < col) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    getAction();
                } else if (!isEmpty(row, col)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    getAction();
                } else {
                    fill(row, col);
                }
            }
        }

        private boolean isWinner(char mark) {
            // horizontal check
            for (int i = 0; i < size; i++) {
                int marks = 0;
                for (int j = 0; j < size; j++) {
                    if (gameState[i * 3 + j] == mark) {
                        marks++;
                    }
                }
                if (marks == 3) {
                    return true;
                }
            }

            // vertical check
            for (int i = 0; i < size; i++) {
                int marks = 0;
                for (int j = 0; j < size; j++) {
                    if (gameState[j * 3 + i] == mark) {
                        marks++;
                    }
                }
                if (marks == 3) {
                    return true;
                }
            }

            // diagonal check
            boolean diagonal1 = gameState[0] == mark && gameState[4] == mark && gameState[8] == mark;
            boolean diagonal2 = gameState[2] == mark && gameState[4] == mark && gameState[6] == mark;
            return diagonal1 || diagonal2;
        }

        private boolean notFilled() {
            for (char mark : gameState) {
                if (mark == '_') {
                    return true;
                }
            }
            return false;
        }

        private boolean unfair() {
            int x = 0;
            int o = 0;
            for (char mark : gameState) {
                if (mark == 'O') {
                    o++;
                }
                if (mark == 'X') {
                    x++;
                }
            }
            return Math.abs(o - x) > 1;
        }

        private boolean isInteger(String str) {
            if (str.isEmpty()) {
                return false;
            }
            return str.matches("-?\\d+");
        }

        private boolean isEmpty(int row, int col) {
            if (gameState[row * 3 + col] == '_') {
                return true;
            } else {
                return false;
            }
        }

        private void fill(int row, int col) {
            int x = 0;
            int o = 0;
            for (char mark : gameState) {
                if (mark == 'O') {
                    o++;
                }
                if (mark == 'X') {
                    x++;
                }
            }
            char currentMark;
            if (x > o) {
                currentMark = 'O';
            } else {
                currentMark = 'X';
            }
            gameState[row * 3 + col] = currentMark;
        }
    }
}

