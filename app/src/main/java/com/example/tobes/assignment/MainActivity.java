package com.example.tobes.assignment;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] buttons = new Button[11];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[1] = (Button) findViewById(R.id.one);
        buttons[2] = (Button) findViewById(R.id.two);
        buttons[3] = (Button) findViewById(R.id.three);
        buttons[4] = (Button) findViewById(R.id.four);
        buttons[5] = (Button) findViewById(R.id.five);
        buttons[6] = (Button) findViewById(R.id.six);
        buttons[7] = (Button) findViewById(R.id.seven);
        buttons[8] = (Button) findViewById(R.id.eight);
        buttons[9] = (Button) findViewById(R.id.nine);
        buttons[10] = (Button) findViewById(R.id.new_game);

        for(int i = 1; i<=10; i++) {
            buttons[i].setOnClickListener(this);    //the computer algorithm that plays against the player
        }
        clearGrid();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        buttons[1].setOnClickListener(this);
        switch(v.getId()) {
            case R.id.one:
                makeMove(1);
                respond();
                break;


            case R.id.two:
                makeMove(2);
                respond();
                break;


            case R.id.three:
                makeMove(3);
                respond();
                break;


            case R.id.four:
                makeMove(4);
                respond();
                break;


            case R.id.five:
                makeMove(5);
                respond();
                break;


            case R.id.six:
                makeMove(6);
                respond();
                break;
            case R.id.seven:
                makeMove(7);
                respond();
                break;

            case R.id.eight:
                makeMove(8);
                respond();
                break;

            case R.id.nine:
                makeMove(9);
                respond();
                break;

            case R.id.new_game:
                clearGrid();
                break;
        }
    }

    public void clearGrid() {
        for (int i = 1; i <= 9; i++) {
            buttons[i].setText("");
            buttons[i]. setEnabled(true);
        }
    }
    public void makeMove (int i) {
        buttons[i].setText("X");
        buttons[i].setEnabled(false);
    }

    public void respond() {
        for (int i = 1; i<= 9; i++) {
            if (buttons[i]. isEnabled()) {
                buttons[i].setText("0");
                buttons[i]. setEnabled(false);
                break;
            }
        }

    }
}
