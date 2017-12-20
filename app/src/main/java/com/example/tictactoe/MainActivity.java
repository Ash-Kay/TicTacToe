package com.example.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0-->X    1-->O
    int activePlayer = 0;
    boolean isGameActive=true;
    //2 means unplayed 0,1 represent x,o
    int []gameState={2,2,2,2,2,2,2,2,2};
    int [][]winningPostion={{0,1,2} ,{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && isGameActive){

            gameState[tappedCounter]=activePlayer;

            if(activePlayer==0){
                counter.setImageResource(R.drawable.x);
                activePlayer=1;
            }
            else{
                counter.setImageResource(R.drawable.o);
                activePlayer=0;
            }

            counter.setScaleX(2);
            counter.setScaleY(2);

            counter.animate().scaleY(1f).scaleX(1f).setDuration(1000);

            for(int[] winningPosition: winningPostion){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2){

                    System.out.println(gameState[winningPosition[0]]);
                    String winnerName="Player 2";
                    isGameActive=false;
                    if(gameState[winningPosition[0]]==0){
                        winnerName="Player 1";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winnerName+" has Won !");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean isgameOver=true;
                    for(int counterState : gameState){
                        if(counterState==2)
                            isgameOver=false;

                    }
                    if(isgameOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("DRAW !");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void playAgain(View view){

        isGameActive=true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        //2 means unplayed 0,1 represent x,o
        //gameState={2,2,2,2,2,2,2,2,2};
        for (int i=0;i<gameState.length;i++) {
            gameState[i]=2;
        }

        GridLayout gridLayout= (GridLayout)findViewById(R.id.gridLayout);

        for (int i=0;i<gridLayout.getChildCount();i++) {

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
