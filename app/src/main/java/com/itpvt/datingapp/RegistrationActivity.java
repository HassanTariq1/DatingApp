package com.itpvt.datingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseAuth.AuthStateListener  listener;
    private  Button btnr;
   private EditText txt, txt1,  mName;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        txt= (EditText) findViewById(R.id.email);
        txt1=(EditText) findViewById(R.id.pass);
        btnr=(Button) findViewById(R.id.btnr);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        mName=(EditText) findViewById(R.id.name);

        auth=FirebaseAuth.getInstance();

        listener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent i =new Intent(RegistrationActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    return;

                }


            }
        };


        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int selectId= radioGroup.getCheckedRadioButtonId();

                final RadioButton radioButton=(RadioButton)findViewById(selectId);


                if(radioButton.getText()== null){

                    return;

                }


                final  String email= txt.getText().toString();
                final  String pass= txt1.getText().toString();
                final  String name= mName.getText().toString();

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){

                            Toast.makeText(RegistrationActivity.this,"Error occur", Toast.LENGTH_SHORT).show();

                        }else {

                            String userId= auth.getCurrentUser().getUid();
                            DatabaseReference userdb= FirebaseDatabase.getInstance().getReference().child("Users").child(radioButton.getText().toString()).child(userId).child("name");

                            userdb.setValue(name);


                        }



                    }
                });

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }

}
