package com.roynaldi.project_uas_rentalcamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListKameraActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOKamera dao;
    boolean isLoading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kamera);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.rvkamera);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOKamera();
        loadData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem < lastVisible + 3){
                    if(!isLoading){
                        isLoading = true;
                        loadData();
                    }
                }
            }
        });
    }

    private void loadData() {

        swipeRefreshLayout.setRefreshing(true);

        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Kamera> kameras = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()){
                    Kamera kamera = data.getValue(Kamera.class);
                    kamera.setKey(data.getKey());
                    kameras.add(kamera);
                    key = data.getKey();
                }
                adapter.setItems(kameras);
                adapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}