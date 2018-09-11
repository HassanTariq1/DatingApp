package com.itpvt.datingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private EditText mNamee,mPh;
    private Button cnfrm, back;

    private ImageView mProfileImage;
private FirebaseAuth auth;
private DatabaseReference datacus;

private   String userId, name, profileImageUrl, phone;
private Uri resulturi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

mNamee=(EditText) findViewById(R.id.namee);
        mPh=(EditText) findViewById(R.id.phone);
        cnfrm=(Button) findViewById(R.id.conf);
        back=(Button) findViewById(R.id.back);
        mProfileImage=(ImageView)findViewById(R.id.profile);


        String usersex= getIntent().getExtras().getString("userSex");

        auth=FirebaseAuth.getInstance();
        userId= auth.getCurrentUser().getUid();

        datacus= FirebaseDatabase.getInstance().getReference().child("Users").child(usersex).child(userId);

   getUserInfo();
   mProfileImage.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

Intent i= new Intent(Intent.ACTION_PICK);
i.setType("image/*");
startActivityForResult(i,1);



       }
   });


        cnfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

saveInfo();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                return;


            }
        });



    }

    private  void getUserInfo(){

        datacus.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

if (dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){

Map<String, Object> map= ( Map<String,Object >) dataSnapshot.getValue();

        if (map.get("name")!=null){


        name= map.get("name").toString();
        mNamee.setText(name);


    }
    if (map.get("phone")!=null){


        phone= map.get("phone").toString();
        mPh.setText(phone);


    }
    if (map.get("profileImageUrl")!=null){


        profileImageUrl= map.get("profileImageUrl").toString();
        Glide.with(getApplication()).load(profileImageUrl).into(mProfileImage);



    }

}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void saveInfo() {

    name=mNamee.getText().toString();
    phone= mPh.getText().toString();

        Map userInfo= new HashMap();
        userInfo.put("name",name);
        userInfo.put("phone",phone);


        datacus.updateChildren(userInfo);

        if(resulturi !=null){


            StorageReference filepath= FirebaseStorage.getInstance().getReference().child("profileImages").child(userId);

            Bitmap bit= null;

            try {
                bit= MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(),resulturi);
            } catch (IOException e) {
                e.printStackTrace();
            }



            ByteArrayOutputStream boas= new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 20, boas);
byte[] data= boas.toByteArray();
            UploadTask upload= filepath.putBytes(data);

            upload.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();

                }
            });

            upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri down= taskSnapshot.getDownloadUrl();

                    Map userInfo= new HashMap();
                    userInfo.put("profileImageUrl",down.toString());

                    datacus.updateChildren(userInfo);
finish();
return;
                }
            });



        }else{

            finish();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== Activity.RESULT_OK   ){

            final Uri   imguri= data.getData();
            resulturi= imguri;
            mProfileImage.setImageURI(resulturi);


        }
    }
}
