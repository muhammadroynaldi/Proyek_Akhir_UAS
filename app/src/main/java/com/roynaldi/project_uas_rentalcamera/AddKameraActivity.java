package com.roynaldi.project_uas_rentalcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddKameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kamera);

        final EditText edit_kodeK = findViewById(R.id.edit_kodeK);
        final EditText edit_merk = findViewById(R.id.edit_merk);
        final EditText edit_harga = findViewById(R.id.edit_harga);

        Button btn_tambah = findViewById(R.id.btn_tambah);
        Button btn_openlist = findViewById(R.id.btn_openlist);
        btn_openlist.setOnClickListener(v->{
            Intent intent = new Intent(AddKameraActivity.this, ListKameraActivity.class);
            startActivity(intent);
        });

        DAOKamera dao = new DAOKamera();
        Kamera kamera_edit = (Kamera)getIntent().getSerializableExtra("EDIT");
        if(kamera_edit != null){
            btn_tambah.setText("UPDATE");
            edit_kodeK.setText(kamera_edit.getKode());
            edit_merk.setText(kamera_edit.getMerk());
            edit_harga.setText(kamera_edit.getHarga());
            btn_openlist.setVisibility(View.GONE);
        }
        else {
            btn_tambah.setText("TAMBAH");
            btn_openlist.setVisibility(View.VISIBLE);
        }

        btn_tambah.setOnClickListener(v -> {
            Kamera kamera = new Kamera(edit_kodeK.getText().toString(), edit_merk.getText().toString(), edit_harga.getText().toString());
            if(kamera_edit==null) {
                dao.add(kamera).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Data Kamera ditambahkan", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else{
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Kode Kamera",edit_kodeK.getText().toString());
                hashMap.put("Merk Kamera",edit_merk.getText().toString());
                hashMap.put("Harga Sewa",edit_harga.getText().toString());

                dao.update(kamera_edit.getKey(), hashMap).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Data Kamera diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }



            /*dao.remove("-NKBMsu3czHtsXfJvVya").addOnSuccessListener(suc->{
                Toast.makeText(this, "Data Kamera dihapus", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });*/
        });


    }
}