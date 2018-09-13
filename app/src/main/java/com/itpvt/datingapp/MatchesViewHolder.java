package com.itpvt.datingapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hassan on 9/12/2018.
 */

public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView matchid;
    public  TextView matchname;
    public ImageView imgm;



    public MatchesViewHolder(View itemView) {
        super(itemView);
itemView.setOnClickListener(this);
matchid= (TextView) itemView.findViewById(R.id.txtmatch);
        matchname= (TextView) itemView.findViewById(R.id.txtmctname);
        imgm=(ImageView) itemView.findViewById(R.id.matimg);



    }

    @Override
    public void onClick(View view) {

    }
}
