package com.itpvt.datingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private cards card_data[];

    private arrayAdapter arrayAdapter;
    private int i;


    private FirebaseAuth auth;

    private String currentUid;
    private DatabaseReference data;

    ListView list;

    List<cards> rowItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

data= FirebaseDatabase.getInstance().getReference().child("Users");
        auth= FirebaseAuth.getInstance();

currentUid=auth.getCurrentUser().getUid();
        CheckUserSex();

        rowItems = new ArrayList<cards>();


        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems );

        SwipeFlingAdapterView flingContainer= (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

                cards obj= (cards) dataObject;
                String userId= obj.getUserId();
                data.child(notusersex).child(userId).child("connections").child("nope").child(currentUid).setValue(true);

                Toast.makeText(MainActivity.this, "Left!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                cards obj= (cards) dataObject;
                String userId= obj.getUserId();
                isConnectionMatch(userId);
                data.child(notusersex).child(userId).child("connections").child("yeps").child(currentUid).setValue(true);
                Toast.makeText(MainActivity.this, "Right!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void isConnectionMatch(String userId) {
         DatabaseReference currentdata=  data.child(usersex).child(currentUid).child("connections").child("yeps").child(userId);
        currentdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(MainActivity.this,"New Connection ",Toast.LENGTH_SHORT).show();
                    data.child(notusersex).child(dataSnapshot.getKey()).child("connections").child("matches").child(currentUid).setValue(true);

                    data.child(usersex).child(currentUid).child("connections").child("matches").child(dataSnapshot.getKey()).setValue(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public String usersex;
    public  String notusersex;
    public  void CheckUserSex(){

        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference maledb= FirebaseDatabase.getInstance().getReference().child("Users").child("Male");


        maledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if(dataSnapshot.getKey().equals(user.getUid())){

                    usersex="Male";
                    notusersex="Female";
                    getOpposite();

                }

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        DatabaseReference femaledb= FirebaseDatabase.getInstance().getReference().child("Users").child("Female");


        femaledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if(dataSnapshot.getKey().equals(user.getUid())){

                    usersex="Female";
                    notusersex="Male";
                    getOpposite();

                }

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }

    public void getOpposite(){

        DatabaseReference oppodb= FirebaseDatabase.getInstance().getReference().child("Users").child(notusersex);


        oppodb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if(dataSnapshot.exists() && !dataSnapshot.child("connections").child("nope").hasChild(currentUid)  && !dataSnapshot.child("connections").child("yeps").hasChild(currentUid)){

//                    al.add(dataSnapshot.child("name").getValue().toString());
                   cards item= new cards(dataSnapshot.getKey(), dataSnapshot.child("name").getValue().toString(), dataSnapshot.child("profileImageUrl").getValue().toString());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();


                }

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }

    public void logoutUser(View view) {

        auth.signOut();
        Intent i = new Intent(MainActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(i);
        finish();
        return;

    }

    public void goToSettings(View view) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        i.putExtra("userSex", usersex);
        startActivity(i);
        return;

    }
}