package com.itpvt.datingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLoginRegistrationActivity extends AppCompatActivity {
    private Button btn, btn1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration);

        btn=(Button) findViewById(R.id.btn);
        btn1=(Button) findViewById(R.id.btn1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ChooseLoginRegistrationActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
                return;

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ChooseLoginRegistrationActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
                return;

            }
        });

    }
}
