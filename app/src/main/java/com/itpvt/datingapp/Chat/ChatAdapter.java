package com.itpvt.datingapp.Chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.Gravity;
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

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null,false);

        ChatViewHolder rov= new ChatViewHolder(layout);

        return rov;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {

holder.txtmsg.setText(chatlist.get(position).getMessage());
if(chatlist.get(position).getCurrentuser()){


    holder.txtmsg.setTextColor(Color.parseColor("#404040"));
    holder.managerl.setBackgroundColor(Color.parseColor("#F4F4F4"));
}
else{



    holder.txtmsg.setTextColor(Color.parseColor("#FFFFFF"));
    holder.managerl.setBackgroundColor(Color.parseColor("#2DB4C8"));

}

    }

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

}
