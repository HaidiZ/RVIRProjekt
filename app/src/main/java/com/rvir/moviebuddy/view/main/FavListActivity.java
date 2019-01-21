package com.rvir.moviebuddy.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.Room;


import android.content.Context;
import android.os.Bundle;
import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.dao.AppDatabase;
import com.rvir.moviebuddy.entity.Favourite;
import com.rvir.moviebuddy.entity.User;

import java.util.List;

public class FavListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        List<Favourite> favList = db.favouriteDao().getAll();

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        // Adapter useresAdapter = new Adapter(Zacasni.getUseres2());
        FavouriteAdapter favAdapter = new FavouriteAdapter((Context) favList, this);
        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
