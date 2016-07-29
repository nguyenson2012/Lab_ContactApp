package com.example.asus.lab_contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> listContact = new ArrayList<>();
    ContactAdapter contactAdapter;
    ListView lvContact;
    DBHelper database;
    Button btnAdd;
    public static final String POSITION = "position";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (ListView) findViewById(R.id.lvInfo);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        setDefaultData();
        setAdapterForListView();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,EditAndCall.class);
                intent.putExtra(POSITION,i);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listContact.clear();
        listContact.addAll(database.getAllCotacts());
        contactAdapter.notifyDataSetChanged();
    }

    private void setAdapterForListView() {
        contactAdapter= new ContactAdapter(listContact,this);
        lvContact.setAdapter(contactAdapter);
    }

    private void setDefaultData() {
//        listContact.add(new Contact("kiki","123"));
//        listContact.add(new Contact("hehe","456"));
//        listContact.add(new Contact("kaka","789"));
//        contactAdapter= new ContactAdapter(listContact,this);
//        lvContact.setAdapter(contactAdapter);
        database=new DBHelper(MainActivity.this);
        //database.insertContact("son","123");
        //database.insertContact("hoang","345");
        listContact=database.getAllCotacts();
    }
}
