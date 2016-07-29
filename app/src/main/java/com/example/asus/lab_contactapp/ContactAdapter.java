package com.example.asus.lab_contactapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus on 7/29/2016.
 */
public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> arrayListContact = new ArrayList<>();
    Context mContext;

    public ContactAdapter(ArrayList<Contact> arrayListContact, Context mContext) {
        this.arrayListContact = arrayListContact;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return arrayListContact.size();
    }

    @Override
    public Contact getItem(int i) {
        return arrayListContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){

            // inflate the layout

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

            convertView = inflater.inflate(R.layout.item_contact, viewGroup, false);

        }
        TextView textViewName = (TextView) convertView.findViewById(R.id.txtName);
        TextView textViewPhone = (TextView) convertView.findViewById(R.id.txtPhone);

        textViewName.setText(arrayListContact.get(position).getName());

        textViewPhone.setText(arrayListContact.get(position).getPhoneNumber());

        return convertView;

    }
}
