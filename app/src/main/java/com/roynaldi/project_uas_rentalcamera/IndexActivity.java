package com.roynaldi.project_uas_rentalcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void addKamera(View view){
        Intent bukaaddKamera = new Intent(IndexActivity.this, AddKameraActivity.class);
        startActivity(bukaaddKamera);
    }

    public void listKamera(View view){
        Intent bukalistKamera = new Intent(IndexActivity.this, ListKameraActivity.class);
        startActivity(bukalistKamera);
    }

    public void tentangAplikasi(View view){
        Intent bukatentangAplikasi = new Intent(IndexActivity.this, InfoActivity.class);
        startActivity(bukatentangAplikasi);
    }
}