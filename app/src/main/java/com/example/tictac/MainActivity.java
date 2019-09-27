package com.example.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   //state is 2 is the space is vacant
    int[] state={2,2,2,2,2,2,2,2,2};

   //0 for player 1st=cross, 1 for player 2nd=circle
    int activePlayer=0;

    int[][] winners={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};




    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tag=Integer.parseInt(counter.getTag().toString());
        LinearLayout linear=(LinearLayout)findViewById(R.id.linear);
        TextView player=(TextView) findViewById(R.id.player);
        if(linear.getVisibility()==View.INVISIBLE) {

            if (state[tag] == 2) {
                state[tag] = activePlayer;
                counter.setTranslationY(-500f);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.circle);
                    activePlayer = 0;
                }
                counter.animate().translationY(0f).setDuration(300);
                int count = 0;
                for (int i : state) {
                    if (i != 2) {
                        count++;
                    }
                }


                for (int[] winner : winners) {
                    if (state[winner[0]] != 2 && state[winner[0]] == state[winner[1]]
                            && state[winner[1]] == state[winner[2]]) {
                        int no = state[winner[0]] + 1;
                        player.setText("Player " + no + " Wins");
                        linear.setVisibility(View.VISIBLE);
                        linear.animate().alpha(1).setDuration(400);
                        break;
                    } else if (count == 9) {
                        player.setText("No Player Wins");
                        linear.setVisibility(View.VISIBLE);
                        linear.animate().alpha(1).setDuration(400);
                    }
                }

            }
        }
    }

    public  void restart(View view){
        LinearLayout linear=(LinearLayout)findViewById(R.id.linear);
        linear.setVisibility(View.INVISIBLE);
        linear.setAlpha(0);
        activePlayer=0;
        for(int i=0;i<state.length;i++){
            state[i]=2;
        }
        GridLayout grid=(GridLayout) findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linear=(LinearLayout)findViewById(R.id.linear);
        linear.setVisibility(View.INVISIBLE);
    }
}
