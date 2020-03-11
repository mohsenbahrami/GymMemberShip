package com.example.gymmembership;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Member> {
    public CustomListAdapter(Context context, ArrayList<Member> note) {
        super(context, 0, note);
    }

    @Override
    public View getView(int position, View row, ViewGroup parent){
        Member current_member = getItem(position); //get the current row of the adapter

        //check to see if the row exists (reused / recreated) or not and set it
        if(row == null)
            row = LayoutInflater.from(getContext()).inflate(R.layout.activity_page_member, parent, false);

        //getting the elements of my row view

        ImageView member_pic = (ImageView)row.findViewById(R.id.imageView_member);
        TextView member_fName = (TextView)row.findViewById(R.id.input_fName);
        TextView member_lName = (TextView)row.findViewById(R.id.input_fName);
        TextView member_dateOfBith = (TextView)row.findViewById(R.id.input_dateOfBirth);
        TextView member_age = (TextView)row.findViewById(R.id.input_age);
        TextView member_address = (TextView)row.findViewById(R.id.input_address);
        TextView member_city = (TextView)row.findViewById(R.id.input_city);
        TextView member_province = (TextView)row.findViewById(R.id.input_province);
        TextView member_barcode = (TextView)row.findViewById(R.id.input_barCode);

        //CheckBox note_checkBox = (CheckBox)row.findViewById(R.id.note_checkBox);

        //setting these elements from the contact
        //member_pic.setImageResource(current_member.getPIC);
        member_fName.setText(current_member.getFirstName());
        member_lName.setText(current_member.getLastName());
        member_dateOfBith.setText(current_member.getDob());
        member_age.setText(current_member.getAge());
        member_address.setText(current_member.getAddress());
        member_city.setText(current_member.getCity());
        member_province.setText(current_member.getProvince());
        member_barcode.setText(current_member.getBarcode());

        return row;
    }

}
