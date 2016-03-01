package com.example.tobes.assignment;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by tobes on 29/02/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText editUsername;
    EditText editPassword;
    TextView logFeedback;

    String dbUsername = "user_db_1429348_NoughtsCrosses";
    String dbPassword = "Passw0rd";
    String connString = "jdbc:jtds:sqlserver://SQL2014.studentwebserver.co.uk";
    Statement stmt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        editUsername = (EditText)findViewById(R.id.editUserName);
        editPassword = (EditText)findViewById(R.id.editPassword);
        logFeedback = (TextView)findViewById(R.id.txtLoginFeedback);
        btnLogin.setOnClickListener(this);
        connectToDb();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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

        //if(checkPassword()) {
            logFeedback.setText("Login Successful");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        //}
        //else {
         //   logFeedback.setText("Login Failed");

         //   if(editUsername.getText().toString().equals("test")) {
       //     editUsername.setText("");
       //     editPassword.setText("");
           // }

      //  }
    }

    public void connectToDb() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Connection DbConn = DriverManager.getConnection(connString, dbUsername, dbPassword);

            logFeedback.setText("Database open");
            stmt = DbConn.createStatement();

        }   catch (Exception e) {
            logFeedback.setText("Connection Error" + e.getMessage());
        }
    }

    public boolean checkPassword(){

        if (editUsername.getText().toString().equals("test") &&
                (editPassword.getText().toString().equals("1234")))
            return true;
        else
            return false;
    }
}
