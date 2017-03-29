
package com.example.sangita.androidlabs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public  class MessageFragment extends Fragment {
    protected static final String ACTIVITY_NAME = "MessageFragment";
    public ChatWindow window;

    // Required empty public constructor
    public MessageFragment() {
        super();
    }
   public MessageFragment(ChatWindow window){
        this.window=window;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    window.removeFragment( MessageFragment.this );
                } else {
                    getActivity().setResult(5);
                    getActivity().finish();
                }
            }
        });
        return view;

    }


}