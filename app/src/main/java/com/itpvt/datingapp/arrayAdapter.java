package com.itpvt.datingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Hassan on 9/10/2018.
 */

public class arrayAdapter extends ArrayAdapter<cards>{

    Context con;


    public arrayAdapter(Context con, int resourceId, List<cards>items){

        super(con,resourceId, items);



    }
public View getView(int position, View convertView, ViewGroup parent){

        cards card_item= getItem(position);
if(convertView==null){
    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);


}
    TextView name=(TextView) convertView.findViewById(R.id.helloText);

    ImageView image=(ImageView) convertView.findViewById(R.id.imagee);


    name.setText(card_item.getName());
    Glide.with(getContext() ).load(card_item.getProfileImageUrl()).into(image);

    return convertView;

}
}
