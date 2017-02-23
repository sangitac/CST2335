package com.example.sangita.androidlabs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CheckBox;



public class ListItemsActivity extends AppCompatActivity {
    protected static final String activity_name = "ListItemsActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton imageButton;
    Switch mySwitch;
    CheckBox mycheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        mySwitch = (Switch) findViewById(R.id.myswitch);

        //set the switch to ON
        mySwitch.setChecked(true);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                //CharSequence text = "switch is ON";
                // int duration = Toast.LENGTH_SHORT;

                if (isChecked) {

                    Toast.makeText(getApplicationContext(), "switch is ON",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "switch is OFF", Toast.LENGTH_LONG).show();
                }
            }
        });

        mycheckBox = (CheckBox) findViewById(R.id.checkBox);

        //attach a listener to check for changes in state
        mycheckBox .setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {


                if (isChecked) {

                    buildDialog();

                }
            }
        });

    }
      //builder pattern to build alert dialog box
       private void buildDialog() {
           AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
           builder.setMessage(R.string.dialog_message)
                   .setTitle(R.string.dialog_title)
                   .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           Intent resultIntent = new Intent();
                           resultIntent.putExtra("Response", "My information to share");
                           setResult(Activity.RESULT_OK, resultIntent);
                           finish();
                           //checkBox.setChecked(false);
                       }
                   })
                   .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // do nothing
                       }
                   })
                   .setIcon(android.R.drawable.ic_dialog_alert)
                   .show();
       }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
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
        super.onStop();
        Log.i(activity_name, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(activity_name, "In onDestroy()");
    }
}
