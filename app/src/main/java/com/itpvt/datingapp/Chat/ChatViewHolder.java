package com.itpvt.datingapp.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itpvt.datingapp.R;

/**
 * Created by Hassan on 9/12/2018.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public  TextView txtmsg;
    public LinearLayout managerl;


    public ChatViewHolder(View itemView) {
        super(itemView);
itemView.setOnClickListener(this);

txtmsg=(TextView) itemView.findViewById(R.id.msg);
managerl=(LinearLayout) itemView.findViewById(R.id.container);



    }

    @Override
    public void onClick(View view) {


    }
}
