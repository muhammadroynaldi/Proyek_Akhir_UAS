package com.roynaldi.project_uas_rentalcamera;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Kamera> list = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<Kamera> kamera){
        list.addAll(kamera);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_kamera, parent, false);
        return new KameraVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KameraVH vh = (KameraVH)holder;
        Kamera kamera = list.get(position);
        vh.txt_kode.setText(kamera.getKode());
        vh.txt_merk.setText(kamera.getMerk());
        vh.txt_harga.setText(kamera.getHarga());
        vh.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->{
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, AddKameraActivity.class);
                        intent.putExtra("EDIT", kamera);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOKamera dao = new DAOKamera();
                        dao.remove(kamera.getKey()).addOnSuccessListener(suc->{
                            Toast.makeText(context, "Data Kamera dihapus", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
