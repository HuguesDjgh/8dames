package com.example.dame;

import java.util.Stack;

public class Logique {
    private static final int BOARD_SIZE = 8;
    private int[][] board;
    private Stack<int[]> placedQueens; // Pile pour stocker les positions des reines placées

    public Logique() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        placedQueens = new Stack<>();
    }
    public boolean placeQueen(int row, int col) {
        board[row][col] = 1;
        placedQueens.push(new int[]{row, col}); // Ajoute la position de la reine à la pile
        return true;
    }
    public void removeLastPlacedQueen() {
        if (!placedQueens.isEmpty()) {
            int[] lastPlacedQueen = placedQueens.pop(); // recup et supprime la dernière reine placée
            board[lastPlacedQueen[0]][lastPlacedQueen[1]] = 0; // Supprime la reine du tableau
        }
    }
    public void recommencerPartie() {
        // reset le plateau
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }
    private boolean isSafe(int row, int col) {
        // verif la rangée et la colonne
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == 1 || board[i][col] == 1) {
                return false;
            }
        }
        // verif les diagonales superieur et inferieurs
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (row - i >= 0 && col - i >= 0 && board[row - i][col - i] == 1) {
                return false; // diagonale haut-gauche
            }
            if (row + i < BOARD_SIZE && col + i < BOARD_SIZE && board[row + i][col + i] == 1) {
                return false; // diagonale bas droite
            }
            if (row - i >= 0 && col + i < BOARD_SIZE && board[row - i][col + i] == 1) {
                return false; // diagonale haut droite
            }
            if (row + i < BOARD_SIZE && col - i >= 0 && board[row + i][col - i] == 1) {
                return false; // diagonale bas gauche
            }
        }
        return true;
    }
    public boolean isSolutionValid() {
        return isSolutionValidUtil(0, 0); // commence la verif a partir de la colonne 0
    }
    private boolean isSolutionValidUtil(int row, int col) {
        if (col >= BOARD_SIZE) {
            return true; // retourne true si la solution marche
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isSafe(i, col)) {
                // Place la dame dans cette case
                board[i][col] = 1;
                // Récursivement, vérifie si la solution est valide avec cette configuration
                if (isSolutionValidUtil(i, col + 1)) {
                    return true;
                }
                // Si la solution n'est pas valide avec cette configuration, retirer la dame de cette case
                board[i][col] = 0;
            }
        }
        // Si aucune configuration n'a fonctionné, retourne false
        return false;
    }
    public boolean isQueenPlaced(int row, int col) {
        return board[row][col] == 1;
    }
}
