package com.example.sangita.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final String activity_name= "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,5);
            }
        });

        Button chatButton = (Button) findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.i(activity_name, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);

            }
        });
    }
    // Call Back method  to get the Message form other Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == 5) {
            Log.i(activity_name, "Returned to StartActivity.onActivityResult");
        }
        if (resultCode == Activity.RESULT_OK) {
            String messagePassed = data.getStringExtra("Response");

           Toast toast = Toast.makeText(getApplicationContext(), messagePassed,
                    Toast.LENGTH_LONG);
            toast.show();

        }
    }

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
    protected void onDestroy(){
        super.onDestroy();
        Log.i(activity_name, "In onDestroy()");
    }
}