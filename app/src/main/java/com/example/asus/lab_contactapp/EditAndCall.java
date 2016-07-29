package com.example.asus.lab_contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditAndCall extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Contact> arrayList;
    Button btnEdit, btnCall, btnMessage;
    EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_call);
        btnEdit= (Button) findViewById(R.id.btnEdit);
        btnCall = (Button) findViewById(R.id.btnCall);
        edtPhone = (EditText) findViewById(R.id.edtPhone2);
        edtName = (EditText) findViewById(R.id.edtName2);
        dbHelper = new DBHelper(EditAndCall.this);
        arrayList = dbHelper.getAllCotacts();
        Intent intent = getIntent();
        final int position = intent.getIntExtra(MainActivity.POSITION, 0);
        edtName.setText(arrayList.get(position).getName());
        edtPhone.setText(arrayList.get(position).getPhoneNumber());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateContact(position+1, edtName.getText().toString(), edtPhone.getText().toString());
                finish();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + edtPhone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(EditAndCall.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }
}
