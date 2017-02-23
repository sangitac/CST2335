package com.example.sangita.androidlabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.content.Context;

public class LoginActivity extends AppCompatActivity {
    protected static final String activity_name= "LoginActivity";
    public static final String Email = "DefaultEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences prefs = getApplicationContext().getSharedPreferences("fileName", Context.MODE_PRIVATE); //String fileName, int mode
        //Context.MODE_PRIVATE;
       // String text = prefs.getString("DefaultEmail", "email@domain.com");
        String text = prefs.getString(Email, "email@domain.com");
        final EditText emailField = (EditText) findViewById(R.id.emailField);
        emailField.setText(text);

      //  int numTimeRun = prefs.getInt("timerun", 0);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        Button login_button = (Button) findViewById(R.id.login_button);  // button??  reference

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString(Email,email);
                edit.commit();

                //setResult(0);
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

    }

   /* public void buttonClick( View view  )
    {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }*/
        protected void onResume() {
            super.onResume();
            Log.i(activity_name, "In onResume()");
        }

        protected void onStart() {
            super.onStart();
            Log.i(activity_name, "In onStart()");
        }

        protected void onPause() {
            super.onPause();
            Log.i(activity_name, "In onPause()");
        }

        protected void onStop() {
            super.onStop();;
            Log.i(activity_name, "In onStop()");
        }
}
