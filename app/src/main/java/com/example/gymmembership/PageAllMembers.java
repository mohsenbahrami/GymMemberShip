package com.example.gymmembership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PageAllMembers extends AppCompatActivity {
    Context context;
    CustomListAdapter adapter;
    ArrayList<Member>members;
    Member member;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_all_members);

        members = new ArrayList<Member>();
        adapter = new CustomListAdapter(this, members);
        listView = (ListView) findViewById(R.id.ls_members);
        listView.setAdapter(adapter);
        context = this;

    }
}
