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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tobes on 29/02/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnNewUser;
    EditText editUsername;
    EditText editPassword;
    TextView logFeedback;
    TextView addFeedback;

    String dbUsername = "user_db_1429348_NoughtsCrosses";
    String dbPassword = "Passw0rd";
    String connString = "jdbc:jtds:sqlserver://SQL2014.studentwebserver.co.uk";
    Statement stmt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnNewUser = (Button) findViewById(R.id.btnNewUser);
        btnNewUser.setOnClickListener(this);
        editUsername = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        logFeedback = (TextView) findViewById(R.id.txtLoginFeedback);
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

        switch (v.getId()) {

            case R.id.btnLogin:
                if (checkPasswordDb()) {
                    logFeedback.setText("Login Successful");
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
            }
                else {
                    logFeedback.setText("Login Failed");
                    editPassword.setText("");
                    editUsername.setText("");
                }
                break;
            case R.id.btnNewUser:
                if (NewUser()) {
                    addFeedback.setText("User Added");
                }
                else {
                    addFeedback.setText("Adding User Failed");
                }
                break;
            default:
                break;
            //}
            //else {
            //   logFeedback.setText("Login Failed");

            //   if(editUsername.getText().toString().equals("test")) {
            //     editUsername.setText("");
            //     editPassword.setText("");
            // }

            //  }
        }
    }

    public void connectToDb() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Connection DbConn = DriverManager.getConnection(connString, dbUsername, dbPassword);

            logFeedback.setText("Database open");
            stmt = DbConn.createStatement();

        } catch (Exception e) {
            logFeedback.setText("Connection Error" + e.getMessage());
        }
    }

    public boolean checkPasswordDb() {
        String sql;
        sql = "Select * FROM Users WHERE username = '" + editUsername.getText().toString() + "'";

        try {
            ResultSet rst = stmt.executeQuery(sql);

            rst.next();

            if (rst.getString("Password").equals(editPassword.getText().toString()))
                return true;
            else
                return false;


        } catch (Exception e) {
            logFeedback.setText("connection error " + e.getMessage());
            return false;
        }
    }

    public boolean NewUser() {
        String sql;
        sql = "INSERT INTO Users VALUES( '" + editUsername.getText().toString() + "','" +
                editPassword.getText().toString() + "')";
        try {
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            logFeedback.setText("New user inserted");
            return false;
        }
    }
}
