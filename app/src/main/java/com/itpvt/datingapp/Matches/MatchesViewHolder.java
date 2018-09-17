package com.itpvt.datingapp.Matches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itpvt.datingapp.Chat.ChatActivity;
import com.itpvt.datingapp.R;

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
// it carry the matchID of user to chat activity for chatting according to the user name or authentication
    @Override
    public void onClick(View view) {
Intent i = new Intent(view.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("matchId", matchid.getText().toString());
        i.putExtras(b);
        view.getContext().startActivity(i);


    }
}
