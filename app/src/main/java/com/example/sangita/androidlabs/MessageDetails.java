package com.example.sangita.androidlabs;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        //create fragment onCreation, pass data from Intent Extras to FragmentTransction
        Bundle extras = getIntent().getExtras();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(extras);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.message_detail_container, fragment);
        transaction.commit();
    }
    public static class MessageFragment extends Fragment {

        public ChatWindow window;

        // Required empty public constructor
        public MessageFragment() {
            super();
        }
        public MessageFragment(ChatWindow window){
            this.window=window;
        }


        @Override
        public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_message, container, false);

            TextView idText = (TextView) view.findViewById(R.id.idTextView);
            idText.setText(String.valueOf(getArguments().getLong("_ID")));

            TextView messageText = (TextView) view.findViewById(R.id.msgTextView);
            messageText.setText(getArguments().getString("MESSAGE"));


            Button delete_Button = (Button) view.findViewById(R.id.msgDeleteButton);
          delete_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (window != null) {
                        window.delete();
                        window.removeFragment();
                    } else {
                        getActivity().setResult(5);
                        getActivity().finish();
                    }
                }
            });
            return view;

        }


    }

}


