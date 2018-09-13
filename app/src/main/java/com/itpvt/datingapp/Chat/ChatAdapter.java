package com.itpvt.datingapp.Chat;

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

public class
ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private List<ChatObject> chatlist;
   private Context cont;


   public ChatAdapter(List<ChatObject> chatlist, Context cont){

       this.chatlist= chatlist;

       this.cont=cont;
   }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null,false);
        RecyclerView.LayoutParams lp= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(lp);
        ChatViewHolder rov= new ChatViewHolder(layout);

        return rov;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
