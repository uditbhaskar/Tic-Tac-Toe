package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //yellow=0,red=1
    int placeValue=0;   
    int[] gameStatus={2,2,2,2,2,2,2,2,2};
    int[][] winRecords={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};   //these are the gameStatus[] positons where if the placeValue are same a player wins.
    Boolean gameActive=true;
    int draw=0;                                                                              //for not letting user play after the message "PLAY AGAIN".
    public void dropIn(View view){
            TextView textView=(TextView)findViewById(R.id.textView3);
            textView.setVisibility(View.INVISIBLE);
            ImageView imageView = (ImageView) view;                           //Red anbd yellow buttons
            int track = Integer.parseInt(imageView.getTag().toString());    //It gives which imageView(Button) is tapped

    if (gameStatus[track]==2 && gameActive && draw==0) {                 //So that imageView can't be changed once clicked and so that no changes occur after game has been won .
        gameStatus[track] = placeValue;
        imageView.setTranslationY(-1500);


            if (placeValue == 0) {
                imageView.setImageResource(R.drawable.yellow);

                placeValue = 1;
            } else if (placeValue == 1) {
                imageView.setImageResource(R.drawable.red);
                placeValue = 0;

            }
            imageView.animate().translationYBy(1500).rotation(1800).setDuration(100);

            //here we check for the gameStatus positions having same placeValues to determine winner if any
            for (int[] winRecord : winRecords) {
                if (gameStatus[winRecord[0]] == gameStatus[winRecord[1]] && gameStatus[winRecord[1]] == gameStatus[winRecord[2]] && gameStatus[winRecord[2]] != 2) {
                    gameActive = false;
                    String msg;

                            if (placeValue == 1) {
                                msg = "Yellow has won!";
                            } else {
                                msg = "Red has won!";
                            }
                            TextView textView1=(TextView)findViewById(R.id.textView);
                            Button replayButton=(Button) findViewById(R.id.replayButton);
                            textView1.setVisibility(View.VISIBLE);
                            replayButton.setVisibility(View.VISIBLE);
                            textView1.setText(msg);

                    }
                }


        }else if(gameActive && gameStatus[track]!=2){               //for draw result!
        TextView textView1=(TextView)findViewById(R.id.textView);
        Button replayButton=(Button) findViewById(R.id.replayButton);
        textView1.setVisibility(View.VISIBLE);
        replayButton.setVisibility(View.VISIBLE);

        textView1.setText("Play Again!");
        draw++;
    }
    }

    public void playAgain(View view){
        Log.i("MSG", "BUTTON PRESSED");
        TextView textView1=(TextView)findViewById(R.id.textView);
        Button replayButton= (Button) findViewById(R.id.replayButton);
        textView1.setVisibility(View.INVISIBLE);
        replayButton.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout= (androidx.gridlayout.widget.GridLayout) findViewById (R.id.gridLayout);


        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        placeValue=0;
        for (int x=0;x<gameStatus.length;x++){
            gameStatus[x]=2;
        }
        gameActive=true;
        TextView textView=(TextView)findViewById(R.id.textView3);
        textView.setVisibility(View.VISIBLE);
        draw=0;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
