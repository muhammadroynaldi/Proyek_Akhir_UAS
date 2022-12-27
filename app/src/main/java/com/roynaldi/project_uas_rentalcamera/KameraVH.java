package com.roynaldi.project_uas_rentalcamera;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KameraVH extends RecyclerView.ViewHolder {

    public TextView txt_kode, txt_merk, txt_harga, txt_option;

    public KameraVH(@NonNull View itemView) {
        super(itemView);
        txt_kode = itemView.findViewById(R.id.txt_kode);
        txt_merk = itemView.findViewById(R.id.txt_merk);
        txt_harga = itemView.findViewById(R.id.txt_harga);
        txt_option = itemView.findViewById(R.id.txt_option);

    }
}
