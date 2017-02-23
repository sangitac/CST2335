package com.example.sangita.androidlabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sangita.androidlabs.R.id.list;

public class ChatWindow extends AppCompatActivity {
    protected static final String activity_name= "ChatWindow";

    ListView listView;
    EditText editText;
    Button sendButton;
    ArrayList<String> msgList;
    ChatAdapter<String> messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        msgList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.editText);
        // editText.setText(text);
        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        sendButton = (Button) findViewById(R.id.send_button);

        //EditText field added to arraylist on button click
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i(activity_name, "User clicked start chat");
                msgList.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged();
                editText.setText("");

            }
        });

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

}
