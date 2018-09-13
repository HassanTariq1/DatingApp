package com.itpvt.datingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {

    private RecyclerView cycle;
private RecyclerView.Adapter adapter;
private RecyclerView.LayoutManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);


        cycle=(RecyclerView) findViewById(R.id.cycle);
        cycle.setNestedScrollingEnabled(false);
        cycle.setHasFixedSize(true);

        manager= new LinearLayoutManager(MatchesActivity.this);
        cycle.setLayoutManager(manager);
        adapter= new MatchesAdapter(getDatabasePath(), MatchesActivity.this);
        cycle.setAdapter(adapter);

        for(int i = 0; i<100;i++){

            MatchesObject object= new MatchesObject(Integer.toString(i));
            resultMat.add(object);
        }
        adapter.notifyDataSetChanged();

    }
private ArrayList<MatchesObject> resultMat = new ArrayList<MatchesObject>();
    private List<MatchesObject> getDatabasePath() {

return  resultMat;


    }
}
