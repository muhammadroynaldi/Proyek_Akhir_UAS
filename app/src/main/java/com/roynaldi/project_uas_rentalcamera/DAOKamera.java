package com.roynaldi.project_uas_rentalcamera;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOKamera {

    private DatabaseReference databaseReference;

    public DAOKamera(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Kamera.class.getSimpleName());
    }

    public Task<Void> add(Kamera kamera){
        //if(kamera == null)
        return databaseReference.push().setValue(kamera);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get()
    {
        return databaseReference;
    }

}
