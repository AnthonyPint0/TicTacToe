package com.antoniocruze.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2, 2};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{1,2,3}, {4,5,6}, {7,8,9},
                            {1,4,7}, {2,5,8}, {3,6,9},
                            {1,5,9}, {3,5,7}};
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.out);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if any player have won
        for (int[] winnPosition: winPositions){
            if (gameState[winnPosition[0]] == gameState[winnPosition[1]] &&
                    gameState[winnPosition[1]] == gameState[winnPosition[2]] &&
                    gameState[winnPosition[0]] != 2){
                //Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winnPosition[0]] == 0){
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }
                //Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.blank);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(R.drawable.blank);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}