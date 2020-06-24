package com.aryan.android.oyo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private ChatListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference(); // to get the object of the database reference

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);
        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                sendMessage();
                return true;
            }
        });


        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    // TODO: Retrieve the display name from the Shared Preferences
    private void setupDisplayName(){
        //SharedPreferences prefs=this.getSharedPreferences(RegisterActivity.CHAT_PREFS,0);
        //mDisplayName=prefs.getString(RegisterActivity.DISPLAY_NAME_KEY,null);
        //Log.d("abc123",mDisplayName);
        //if(mDisplayName==null)
        mDisplayName="You";

    }

    private void sendMessage() {

        // TODO: Grab the text the user typed in and push the message to Firebase
        String input=mInputText.getText().toString();
        if(!input.equals("")){
            InstantMessage chat=new InstantMessage(input,mDisplayName);
            // databse reference is a particular location in our database
            // child("messages") means all our messages are placed at a particular location called messages
            //push method to get the reference to this child location
            //setValue to actually write the chat in our chat object
            mDatabaseReference.child("messages").push().setValue(chat);
            // chat varibale is the location where the data is saved and setvalue is responsble for accomplishing the task
            mInputText.setText("");
        }

    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
    public void onStart(){
        super.onStart();
        adapter=new ChatListAdapter(this, mDatabaseReference,mDisplayName);
        mChatListView.setAdapter(adapter);
    }
    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.
        // freeing up resources-- stop checking the updates on the firebase database
        adapter.cleanup();

    }

}
