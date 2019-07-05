package com.example.devyanshitiwari.finalprojecttennis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class game extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
   // private ArrayList<String> arrayList;
    TextView p1,p2,formatgame,lastsetgame,scorep1,scorep2;
    String name1ofplayer,name2ofplayer;
    int score1,score2;
    Button fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        name1ofplayer = intent.getStringExtra("NAME1");
        name2ofplayer = intent.getStringExtra("NAME2");
        String format = intent.getStringExtra("FORMAT");
        String lastset = intent.getStringExtra("LASTSET");
        setResult(RESULT_OK, intent);
        p1=(TextView)findViewById(R.id.player1name);
        scorep1=(TextView)findViewById(R.id.scoreofPlayer1);
        scorep2=(TextView)findViewById(R.id.scoreofPlayer2);
        p2=(TextView)findViewById(R.id.player2name);
        formatgame=(TextView)findViewById(R.id.formatgame);
        lastsetgame=(TextView)findViewById(R.id.lastsetgame);
        p2.setText(name2ofplayer);
        p1.setText(name1ofplayer);
        formatgame.setText(format);
        lastsetgame.setText(lastset);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.addPlayerDetail(name1ofplayer);
        databaseHelper.addPlayerDetail(name2ofplayer);
        databaseHelper.addFormatDetail(format);
        databaseHelper.addlastSetDetail(lastset);
        fin=(Button)findViewById(R.id.fin);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score1>score2)
                {

                    databaseHelper.addVictoryDetail("player 1 won");
                    Toast.makeText(getApplicationContext(), "Player 1 WON!", Toast.LENGTH_SHORT).show();


                }
                else if(score2>score1)
                {
                    databaseHelper.addVictoryDetail("player 2 won");
                    Toast.makeText(getApplicationContext(), "Player 2 WON!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    databaseHelper.addVictoryDetail("match was tie");
                    Toast.makeText(getApplicationContext(), "SEEMS LIKE EVERYONE WON", Toast.LENGTH_SHORT).show();

                }
                Toast.makeText(getApplicationContext(), "Stored Successfully!", Toast.LENGTH_SHORT).show();

            }
        });
      ;

    }
    public void onClick(View view)
    {
        int id=view.getId();
        switch(id) {
            case R.id.ten1:
            { score1 = score1 + 10;
              scorep1.setText(String.valueOf(score1));

                break;}
            case R.id.ten2:{
                score2 = score1 + 10;
                scorep2.setText(String.valueOf(score2));
                break;}
        }

    }
    }

