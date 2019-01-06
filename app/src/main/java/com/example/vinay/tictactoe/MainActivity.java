package com.example.vinay.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buRestart(View view)
    {
        recreate();
        Toast.makeText(this, "New Game", Toast.LENGTH_LONG).show();
    }
    int numPlayer = 0;
    int wins = 0;
    public void buPlayers1(View view)
    {
        numPlayer = 1;
        findViewById(R.id.btplayers1).setEnabled(false);
        findViewById(R.id.btplayers2).setEnabled(false);
    }
    public void buPlayers2(View view)
    {
        numPlayer = 2;
        findViewById(R.id.btplayers1).setEnabled(false);
        findViewById(R.id.btplayers2).setEnabled(false);
    }
    public void buClick(View view)
    {
        if(numPlayer == 0)
        {
            Toast.makeText(this, "Please Select number of players", Toast.LENGTH_SHORT).show();
            return;
        }
        Button buSelected = (Button)view;
        int cellId = 0;
        switch(buSelected.getId())
        {
            case R.id.bt1: cellId = 1; break;
            case R.id.bt2: cellId = 2; break;
            case R.id.bt3: cellId = 3; break;
            case R.id.bt4: cellId = 4; break;
            case R.id.bt5: cellId = 5; break;
            case R.id.bt6: cellId = 6; break;
            case R.id.bt7: cellId = 7; break;
            case R.id.bt8: cellId = 8; break;
            case R.id.bt9: cellId = 9; break;
        }
        playGame(cellId, buSelected);
    }
    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();
    int player = 1;
    int count = 0;
    public void playGame(int cellId, Button buSelected)
    {
        if(player == 1)
        {
            count++;
            buSelected.setText("X");
            buSelected.setBackgroundColor(Color.GREEN);
            player1.add(cellId);
            checkWinner();
            if(count < 9 && wins == 0)
            {
                player = 2;
                if(numPlayer == 1) {
                    autoPlay();
                }
            }
        }
        else if(player == 2)
        {
            count++;
            buSelected.setText("O");
            buSelected.setBackgroundColor(Color.BLUE);
            player2.add(cellId);
            player = 1;
            checkWinner();
        }
        buSelected.setEnabled(false);
    }
    public void checkWinner()
    {
        int winner = -1;
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){winner = 1;}
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3)){winner = 2;}

        else if(player1.contains(4) && player1.contains(5) && player1.contains(6)){winner = 1;}
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6)){winner = 2;}

        else if(player1.contains(7) && player1.contains(8) && player1.contains(9)){winner = 1;}
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9)){winner = 2;}

        else if(player1.contains(1) && player1.contains(4) && player1.contains(7)){winner = 1;}
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7)){winner = 2;}

        else if(player1.contains(2) && player1.contains(5) && player1.contains(8)){winner = 1;}
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8)){winner = 2;}

        else if(player1.contains(3) && player1.contains(6) && player1.contains(9)){winner = 1;}
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9)){winner = 2;}

        else if(player1.contains(1) && player1.contains(5) && player1.contains(9)){winner = 1;}
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9)){winner = 2;}

        else if(player1.contains(3) && player1.contains(5) && player1.contains(7)){winner = 1;}
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7)){winner = 2;}
        if(winner != -1 || count == 9)
        {
            wins = 1;
            if(winner == 1)
            {
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_LONG).show();
            }
            else if(winner == 2)
            {
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_LONG).show();
            }
            else if(count == 9)
            {
                Toast.makeText(this, "Match Draw", Toast.LENGTH_LONG).show();
            }
            findViewById(R.id.bt1).setEnabled(false);
            findViewById(R.id.bt2).setEnabled(false);
            findViewById(R.id.bt3).setEnabled(false);
            findViewById(R.id.bt4).setEnabled(false);
            findViewById(R.id.bt5).setEnabled(false);
            findViewById(R.id.bt6).setEnabled(false);
            findViewById(R.id.bt7).setEnabled(false);
            findViewById(R.id.bt8).setEnabled(false);
            findViewById(R.id.bt9).setEnabled(false);
        }

    }
    public void autoPlay()
    {
        ArrayList<Integer> emptycells = new ArrayList< Integer>();
        for(int i = 1; i <= 9
                ; i++)
        {
            if(!player1.contains(i) && !player2.contains(i)) {
                emptycells.add(i);
            }
        }
        if(emptycells.size() == 0)
        {
            count = 9;
            checkWinner();
        }
        int cellId = 0;
        for(int i = 1; i <= 9; i += 3) {
            if (player2.contains(i) && player2.contains(i + 1) && emptycells.contains(i + 2)) {
                cellId = i + 2;
                break;
            } else if (player2.contains(i + 1) && player2.contains(i + 2) && emptycells.contains(i)) {
                cellId = i;
                break;
            } else if (player2.contains(i) && player2.contains(i + 2) && emptycells.contains(i + 1)) {
                cellId = i + 1;
                break;
            }
        }
        if(cellId == 0)
        for(int i = 1; i <= 3; i += 1) {
            if (player2.contains(i) && player2.contains(i + 3) && emptycells.contains(i + 6)) {
                cellId = i + 6;
                break;
            } else if (player2.contains(i + 3) && player2.contains(i + 6) && emptycells.contains(i)) {
                cellId = i;
                break;
            } else if (player2.contains(i) && player2.contains(i + 6) && emptycells.contains(i + 3)) {
                cellId = i + 3;
                break;
            }
        }
        if(cellId == 0)
        {
            if(player2.contains(1) && player2.contains(5) && emptycells.contains(9))
            {
                cellId = 9;
            }
            else if(player2.contains(5) && player2.contains(9) && emptycells.contains(1))
            {
                cellId = 1;
            }
            else if(player2.contains(1) && player2.contains(9) && emptycells.contains(5)) {
                cellId = 5;
            }
        }
        if(cellId == 0)
        {
            if(player2.contains(3) && player2.contains(5) && emptycells.contains(7))
            {
                cellId = 7;
            }
            else if(player2.contains(5) && player2.contains(7) && emptycells.contains(3))
            {
                cellId = 3;
            }
            else if(player2.contains(3) && player2.contains(7) && emptycells.contains(5))
            {
                cellId = 5;
            }
        }
        if(cellId == 0)
        for(int i = 1; i <= 9; i += 3) {
            if(player1.contains(i) && player1.contains(i+1) && emptycells.contains(i+2))
            {
                cellId = i+2;
                break;
            }
            else if(player1.contains(i+1) && player1.contains(i+2) && emptycells.contains(i))
            {
                cellId = i;
                break;
            }
            else if(player1.contains(i) && player1.contains(i+2) && emptycells.contains(i+1))
            {
                cellId = i+1;
                break;
            }
        }
        if(cellId == 0)
        {
            for(int i = 1; i <= 3; i += 1) {
                if(player1.contains(i) && player1.contains(i+3) && emptycells.contains(i+6))
                {
                    cellId = i+6;
                    break;
                }
                else if(player1.contains(i+3) && player1.contains(i+6) && emptycells.contains(i))
                {
                    cellId = i;
                    break;
                }
                else if(player1.contains(i) && player1.contains(i+6) && emptycells.contains(i+3))
                {
                    cellId = i+3;
                    break;
                }
            }
        }
        if(cellId == 0)
        {
            if(player1.contains(1) && player1.contains(5) && emptycells.contains(9))
            {
                cellId = 9;
            }
            else if(player1.contains(5) && player1.contains(9) && emptycells.contains(1))
            {
                cellId = 1;
            }
            else if(player1.contains(1) && player1.contains(9) && emptycells.contains(5)) {
                cellId = 5;
            }
        }
        if(cellId == 0)
        {
            if(player1.contains(3) && player1.contains(5) && emptycells.contains(7))
            {
                cellId = 7;
            }
            else if(player1.contains(5) && player1.contains(7) && emptycells.contains(3))
            {
                cellId = 3;
            }
            else if(player1.contains(3) && player1.contains(7) && emptycells.contains(5))
            {
                cellId = 5;
            }
        }

        if(cellId == 0)
        {
            if(emptycells.contains(5))
            {
                cellId = 5;
            }

        }
        if(cellId == 0)
        {
            Random r = new Random();
            int ind = r.nextInt(emptycells.size());
            cellId = emptycells.get(ind);
        }
        Button button;
        switch (cellId)
        {
            case 1: button = findViewById(R.id.bt1); break;
            case 2: button = findViewById(R.id.bt2); break;
            case 3: button = findViewById(R.id.bt3); break;
            case 4: button = findViewById(R.id.bt4); break;
            case 5: button = findViewById(R.id.bt5); break;
            case 6: button = findViewById(R.id.bt6); break;
            case 7: button = findViewById(R.id.bt7); break;
            case 8: button = findViewById(R.id.bt8); break;
            case 9: button = findViewById(R.id.bt9); break;
            default: button = findViewById(R.id.bt10);
        }
        playGame(cellId, button);
    }
}
