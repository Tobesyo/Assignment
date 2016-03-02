package com.example.tobes.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * Created by tobes on 01/03/2016.
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    Animation animFade;

    Button btnContinue;
    ImageView imgView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main);

        animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);

        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    public void onClick(View v) {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);


        }
    }
