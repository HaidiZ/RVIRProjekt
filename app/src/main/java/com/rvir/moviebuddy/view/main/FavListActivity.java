package com.rvir.moviebuddy.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.dao.AppDatabase;
import com.rvir.moviebuddy.dao.DbUtil;
import com.rvir.moviebuddy.dao.FavouriteDao;
import com.rvir.moviebuddy.dao.UserDao;
import com.rvir.moviebuddy.entity.Favourite;
import com.rvir.moviebuddy.entity.User;

import java.util.List;

public class FavListActivity extends AppCompatActivity {
    AppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_list_2);
        FavouriteDao favouriteDao = DbUtil.getDb(getApplicationContext()).favouriteDao();
        favouriteDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Favourite>>() {
                    @Override
                    public void onSuccess(List<Favourite> favourites) {
                        Log.d("MAIN", "Število uporabnikov: " + favourites.size());
                        RecyclerView recyclerView = findViewById(R.id.fav_list_recView);
                        FavouriteAdapter zaposleniAdapter = new FavouriteAdapter(favourites);
                        recyclerView.setAdapter(zaposleniAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(FavListActivity.this));
                        Toast.makeText(getApplicationContext(), "seznam posodobljen", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
       /* AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        List<Favourite> favList = db.favouriteDao().getAll();

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        // Adapter useresAdapter = new Adapter(Zacasni.getUseres2());
        FavouriteAdapter favAdapter = new FavouriteAdapter((Context) favList, this);
        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
*/
    }
}
