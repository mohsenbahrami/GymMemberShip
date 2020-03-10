package com.example.gymmembership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btn_newMember,btn_checkin,btn_allMember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_newMember = (Button)findViewById(R.id.btn_newMember);
        btn_checkin = (Button)findViewById(R.id.btn_checkIn);
        btn_allMember = (Button)findViewById(R.id.btn_allMembers);

        btn_newMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PageMember.class);
                startActivity(intent);
            }
        });

        btn_allMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PageAllMembers.class);
                startActivity(intent);

            }
        });
    }

}
