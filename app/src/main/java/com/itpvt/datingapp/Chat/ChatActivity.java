package com.itpvt.datingapp.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itpvt.datingapp.MatchesAdapter;
import com.itpvt.datingapp.MatchesObject;
import com.itpvt.datingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView cycle;
    private RecyclerView.Adapter adapterchat;
    private RecyclerView.LayoutManager managerchat;
    private  String currentUser, matchId, chatId;

    private EditText smstxt;
    private Button btntxt;
    DatabaseReference datab, datach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        smstxt=(EditText)findViewById(R.id.editsms);
        btntxt=(Button)findViewById(R.id.send);
getChatId();

        datab= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser).child("connections").child("matches").child(matchId).child("chatId");

        datach= FirebaseDatabase.getInstance().getReference().child("Chat");
matchId= getIntent().getExtras().getString("matchId");
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();

        cycle=(RecyclerView) findViewById(R.id.cycle);
        cycle.setNestedScrollingEnabled(false);
        cycle.setHasFixedSize(true);

        managerchat= new LinearLayoutManager(ChatActivity.this);
        cycle.setLayoutManager(managerchat);
        adapterchat= new ChatAdapter(getDataSetChat(), ChatActivity.this);
        cycle.setAdapter(adapterchat);
        
        btntxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSms();
                
                
            }
        });
        
    }

    private void SendSms() {


        String sendsms= smstxt.getText().toString();
        if(!sendsms.isEmpty()){

            DatabaseReference  newmsg= datach.push();

            Map newMsg= new HashMap();
            newMsg.put( "createdByUser", currentUser);
            newMsg.put( "text", sendsms);

            newmsg.setValue(newMsg);



        }

        smstxt.setText(null);
    }


    private  void getChatId(){
        datab.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    chatId= dataSnapshot.getValue().toString();
                    datach=datach.child(chatId);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private ArrayList<ChatObject> resultChat = new ArrayList<ChatObject>();
    private List<ChatObject> getDataSetChat() {

        return  resultChat;


    }
}