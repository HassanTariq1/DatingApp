package com.itpvt.datingapp.Matches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itpvt.datingapp.R;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {

    private RecyclerView cycle;
private RecyclerView.Adapter adapter;
private RecyclerView.LayoutManager manager;
private  String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);




// use of a recyclerview to displat a matches

        cycle=(RecyclerView) findViewById(R.id.cycle);
        cycle.setNestedScrollingEnabled(false);
        cycle.setHasFixedSize(true);

        manager= new LinearLayoutManager(MatchesActivity.this);
        cycle.setLayoutManager(manager);
        adapter= new MatchesAdapter(getDatabasePath(), MatchesActivity.this);
        cycle.setAdapter(adapter);
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        
        getUserMatchId();
    }

// function get the matches which are made of user with other friends


    private void getUserMatchId() {


        DatabaseReference matchDb= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser).child("connections").child("matches");
        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
for(DataSnapshot match: dataSnapshot.getChildren()){

    FetchMatchInfo(match.getKey());

}
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void FetchMatchInfo(String key) {

        DatabaseReference userDb= FirebaseDatabase.getInstance().getReference().child("Users").child(key);
userDb.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){
            String userId= dataSnapshot.getKey();


String name="";
String profileImageUrl="";

            if(dataSnapshot.child("name").getValue()!=null){
                name= dataSnapshot.child("name").getValue().toString();



            }
            if(dataSnapshot.child("profileImageUrl").getValue()!=null){
                profileImageUrl= dataSnapshot.child("profileImageUrl").getValue().toString();



            }

            MatchesObject object= new MatchesObject(userId, name, profileImageUrl);
            resultMat.add(object);

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }

    private ArrayList<MatchesObject> resultMat = new ArrayList<MatchesObject>();
    private List<MatchesObject> getDatabasePath() {

return  resultMat;


    }
}
