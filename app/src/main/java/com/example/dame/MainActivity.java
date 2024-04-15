package com.example.dame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridLayout boardGridLayout;
    private Logique logic;
    private int[][] colors = {
            {Color.YELLOW, Color.rgb(204, 102, 0)},
            {Color.rgb(204, 102, 0), Color.YELLOW}
    };

    private int remainingQueens = 8; // nombre de dame de base

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardGridLayout = findViewById(R.id.boardGridLayout);
        setupBoard();
        logic = new Logique();
    }

    private void setupBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Button button = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = getResources().getDisplayMetrics().widthPixels / 8; // Largeur carrée
                params.height = params.width; // mettre meme valeur que le width
                params.columnSpec = GridLayout.spec(j);
                params.rowSpec = GridLayout.spec(i);
                button.setLayoutParams(params);
                button.setBackgroundColor(colors[i % 2][j % 2]); // alternation des couleurs pour le plateau
                button.setTag(i * 8 + j); // met un tag au bouton pour mieux les identifier
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) button.getTag();
                        int row = position / 8;
                        int col = position % 8;
                        placeQueen(row, col);
                    }
                });
                boardGridLayout.addView(button);
            }
        }

        // Bouton pour verif la solution
        Button checkSolutionButton = findViewById(R.id.button_check_solution);
        checkSolutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logic.isSolutionValid()) {
                    Toast.makeText(MainActivity.this, "Gagné ! Vous avez placé les 8 dames de manière valide.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "La disposition actuelle n'est pas une solution valide.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button removeQueenButton = findViewById(R.id.button_remove_queen);
        removeQueenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.removeLastPlacedQueen(); // retire la dernière dame placée
                if(remainingQueens <8){
                    remainingQueens++; // ajoute 1 au compteur
                }
                TextView textViewQueensRemaining = findViewById(R.id.textView_queens_remaining);
                textViewQueensRemaining.setText("Dames restantes : " + remainingQueens);
                updateBoard();
            }
        });

        // Bouton pour recommencer une partie
        Button recommencerPartieButton = findViewById(R.id.button_recommencer);
        recommencerPartieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.recommencerPartie(); // Reinit plateau
                remainingQueens = 8; // reset le nombre de dames
                updateBoard();
                TextView textViewQueensRemaining = findViewById(R.id.textView_queens_remaining);
                textViewQueensRemaining.setText("Dames restantes : " + remainingQueens); // reset le texte
            }
        });

    }
    private void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = findButtonByPosition(i, j);
                if (button != null) {
                    if (logic.isQueenPlaced(i, j)) {
                        button.setBackgroundResource(R.drawable.queen_icon);
                    } else {
                        button.setBackgroundColor(colors[i % 2][j % 2]); // remet la couleur de la case
                    }
                }
            }
        }
    }

    private Button findButtonByPosition(int row, int col) {
        int position = row * 8 + col;
        for (int i = 0; i < boardGridLayout.getChildCount(); i++) {
            View child = boardGridLayout.getChildAt(i);
            if (child instanceof Button && (int) child.getTag() == position) {
                return (Button) child;
            }
        }
        return null; // Retourne null si le bouton n'est pas trouvé
    }

    private void placeQueen(int row, int col) {
        if (remainingQueens > 0) {
            boolean placed = logic.placeQueen(row, col);
            if (placed) {
                // placage de dame
                remainingQueens--; // enlever 1 du compteur
                TextView textViewQueensRemaining = findViewById(R.id.textView_queens_remaining);
                textViewQueensRemaining.setText("Dames restantes : " + remainingQueens); // met a jour le compteur

                // Trouver le bouton correspondant a l'endroit ou la dame a ete posee
                Button button = findButtonByPosition(row, col);

                // Vérif si le bouton a bien ete trouve
                if (button != null) {
                    button.setBackgroundResource(R.drawable.queen_icon);
                }
            }
        } else {
            Toast.makeText(MainActivity.this, "Vous avez déjà placé toutes les dames.", Toast.LENGTH_SHORT).show();
        }
    }

}
