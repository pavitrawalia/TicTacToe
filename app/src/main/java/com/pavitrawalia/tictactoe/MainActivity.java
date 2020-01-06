package com.pavitrawalia.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] btn=new Button[3][3];
    private boolean player1turn = true;
    private int roundcount;
    private int player1point;
    private int player2point;
    private Button textplayer1;
    private Button textplayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textplayer1= findViewById (R.id.button12);
        textplayer2=findViewById (R.id.button13);
        updatePointsText ();
        int p=1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID="button"+p;
                p++;
                int resID =getResources ().getIdentifier (buttonID,"id",getPackageName ());
                btn[i][j]=findViewById (resID);
                btn[i][j].setOnClickListener (this);
            }
        }
        Button buttonreset=findViewById (R.id.button10);
        buttonreset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                resetgame();
            }
        });

        }




    @Override
    public void onClick (View v) {
        if (!((Button) v).getText ().toString ().equals ("")){
            return;
        }
        if (player1turn){
            ((Button) v).setText ("X");
        }
        else{
            ((Button) v).setText ("O");
        }
        roundcount++;
        if(checkforwin ()){
            if(player1turn){
                player1wins();
            }
            else{
                player2wins();
            }
        }
        else if(roundcount == 9){
            draw();
        }else{
            player1turn = !player1turn;
        }
    }

    private void player1wins () {
        player1point++;
        Toast.makeText (this,"Player 1 wins!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }


    private void player2wins () {
        player2point++;
        Toast.makeText (this,"Player 2 wins!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void updatePointsText () {
        textplayer1.setText ("Player 1:"+player1point);
        textplayer2.setText ("Player 2:"+player2point);
    }

    private void resetBoard () {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                btn[i][j].setText ("");
            }
        }
        roundcount = 0;
        player1turn = true;

    }

    private void draw () {
        Toast.makeText (this,"Draw!!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private boolean checkforwin(){
        String[][] value=new String[3][3];
        int p=1;
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                value[i][j] = btn[i][j].getText ().toString ();
            }
        }
        for(int i=0;i<3;i++){
            if (value[i][0].equals(value[i][1]) && value[i][0].equals(value[i][2]) && !value[i][0].equals("")) {
                return true;
            }
        }
        for(int j=0;j<3;j++){
            if (value[0][j].equals(value[1][j]) && value[0][j].equals(value[2][j]) && !value[0][j].equals("")) {
                return true;
            }
        }
        if (value[0][0].equals(value[1][1]) && value[0][0].equals(value[2][2]) && !value[0][0].equals("")) {
            return true;
        }
        if (value[0][2].equals(value[1][1]) && value[0][2].equals(value[2][0]) && !value[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void resetgame(){
        player1point=0;
        player2point=0;
        updatePointsText ();
        resetBoard ();
    }
}
