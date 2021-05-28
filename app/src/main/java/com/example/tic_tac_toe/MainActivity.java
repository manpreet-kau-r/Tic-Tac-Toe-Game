package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    boolean gameActive = true;

    // 0 - X
    // 1 - O
    int activePlayer = 0;

    // 0 - X
    // 1 - O
    // 2 - Null
    int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };

    int[][] winPositions = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };

    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++)
            gameState[i] = 2;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive)
            gameReset(view);
        else if(gameState[tappedImage]==2)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if(activePlayer==0)
            {
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else
            {
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // check if any player has won
        for(int[] winPosition : winPositions)
        {
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2)
            {
                // somebody has won - who?
                String winnerStr;
                if(gameState[winPosition[0]]==0)
                    winnerStr = "X has won";
                else
                    winnerStr = "O has won";

                // update status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                gameActive = false;
            }
        }

        if(gameActive)
        {
            int i;
            for (i = 0; i < 9; i++)
            {
                if (gameState[i] == 2)
                    break;
            }

            if (i == 9)
            {
                TextView status = findViewById(R.id.status);
                String str = "Draw Game";
                status.setText(str);
                gameActive = false;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}