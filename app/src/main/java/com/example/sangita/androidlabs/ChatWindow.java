package com.example.sangita.androidlabs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sangita.androidlabs.R.id.list;

public class ChatWindow extends AppCompatActivity {
    protected static final String activity_name= "ChatWindow";

    protected ListView listView;
    protected EditText editText;
    protected Button sendButton;
    protected ArrayList<String> msgList;
    protected ChatAdapter<String> messageAdapter;
    protected ChatDatabaseHelper chatDbHelper ;
    protected SQLiteDatabase db;
    protected FrameLayout frameLayout;
    boolean isFrameLayout;
    private long idToDelete;
    private int positionID;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(activity_name, "I AM CALLED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        msgList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.editText);
        sendButton = (Button) findViewById(R.id.send_button);
        isFrameLayout = (findViewById(R.id.message_detail_container) != null);
        Log.i(activity_name, "Frame layout found: " + String.valueOf(frameLayout != null));
        //create database
        chatDbHelper = new ChatDatabaseHelper(this);
        db =  chatDbHelper.getWritableDatabase();


        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i(activity_name, "User clicked send button");

                //EditText field added to arraylist on button click
                String strMsg = editText.getText().toString();
                msgList.add( strMsg);
                messageAdapter.notifyDataSetChanged();
                editText.setText("");

                //save message to database
                ContentValues values = new ContentValues();
                values.put(chatDbHelper.KEY_MESSAGE, strMsg);
                db.insert(chatDbHelper.TABLE_NAME , null, values);

            }
        });

        //read message from table
      /*  Cursor cursor = db.query(chatDbHelper.TABLE_NAME,
                new String[] { chatDbHelper.KEY_MESSAGE},
               " Message = ?",new String[] {"dd"}, null, null, null, null);*/

        // for reading  all message

        /*Cursor cursor = db.query(chatDbHelper.TABLE_NAME,
                new String[] { chatDbHelper.KEY_MESSAGE},
                null, null, null, null, null, null);
        */

        cursor = db.query(false, chatDbHelper.TABLE_NAME,
                new String[] { chatDbHelper.KEY_ID,chatDbHelper.KEY_MESSAGE},
                null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast() ){
            String msgTmp = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            msgList.add(msgTmp);
            Log.i(activity_name, "SQL MESSAGE:" + msgTmp);
            cursor.moveToNext();


        // count the number of column and print name of each column
        Log.i(activity_name, "Cursor's column count =" + cursor.getColumnCount());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(activity_name, "Column" + (i+1) + "; " + cursor.getColumnName(i));
        }

        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ChatWindow.this, MessageDetails.class);
                    intent.putExtra("_ID", messageAdapter.getItemId(position));
                    intent.putExtra("MESSAGE", messageAdapter.getItem(position));

                    idToDelete = messageAdapter.getItemId(position);
                    positionID = position;


                    if (isFrameLayout) {
                        Fragment mFragment = new MessageDetails.MessageFragment(ChatWindow.this);
                        mFragment.setArguments(intent.getExtras());
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.message_detail_container, mFragment).addToBackStack("STACK");
                        transaction.commit();

                    } else {
                        startActivityForResult(intent, 5);
                    }
                }
            });
        }

    }

    public void delete(){
        Log.i(activity_name, "DELETE");
        //final SQLiteDatabase db = chatDbHelper.getWritableDatabase();

        db.delete(chatDbHelper.TABLE_NAME, chatDbHelper.KEY_ID + "=" + String.valueOf(idToDelete), null);

        Log.i(activity_name, String.valueOf(idToDelete));
        msgList.remove(positionID);
        cursor = db.query(false,chatDbHelper.TABLE_NAME,
                new String[] { chatDbHelper.KEY_ID,chatDbHelper.KEY_MESSAGE},
                null, null, null, null, null, null);
        messageAdapter.notifyDataSetChanged();
    }

    public void removeFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fm.popBackStack();
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 5) {
            delete();
        }
    }
        public class ChatAdapter<String> extends ArrayAdapter<String> {
            public ChatAdapter(Context ctx) {
                super(ctx, 0);
            }

            public int getCount() {
                return msgList.size();
            }

            public String getItem(int position) {
                return (String)msgList.get(position);
            }

            public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

                View result = null;
                if (position % 2 == 0)
                    result = inflater.inflate(R.layout.chat_row_incoming, null);
                else
                    result = inflater.inflate(R.layout.chat_row_outgoing, null);

                TextView message= (TextView) result.findViewById(R.id.message_text);
                message.setText(getItem(position).toString());

                return result;

            }

            public long getItemId(int position) {
                cursor.moveToPosition(position);
                return cursor.getLong(cursor.getColumnIndex(chatDbHelper.KEY_ID));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //close database
        db.close();
    }

}
