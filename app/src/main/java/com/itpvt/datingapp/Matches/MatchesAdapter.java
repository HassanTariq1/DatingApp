package com.itpvt.datingapp.Matches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itpvt.datingapp.R;

import java.util.List;

/**
 * Created by Hassan on 9/12/2018.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> {

    private List<MatchesObject> matchlist;
   private Context cont;


   public  MatchesAdapter(List<MatchesObject> matchlist, Context cont){

       this.matchlist= matchlist;

       this.cont=cont;
   }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null,false);

       MatchesViewHolder view= new MatchesViewHolder(layout);


        return view;

    }
// set the image on matches or make it default......



    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {

       holder.matchid.setText(matchlist.get(position).getUserId());
        holder.matchname.setText(matchlist.get(position).getName());
        if(!matchlist.get(position).getProfileImageUrl().equals("default")) {
            Glide.with(cont).load(matchlist.get(position).getProfileImageUrl()).into(holder.imgm);
        }

    }

    @Override
    public int getItemCount() {
        return matchlist.size();
    }
}
