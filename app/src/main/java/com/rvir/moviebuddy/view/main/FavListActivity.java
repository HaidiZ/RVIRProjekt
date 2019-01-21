package com.rvir.moviebuddy.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.Room;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.dao.AppDatabase;
import com.rvir.moviebuddy.entity.Favourite;
import com.rvir.moviebuddy.entity.User;

import java.util.List;

public class FavListActivity extends AppCompatActivity {
    AppDatabase myAppDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_list_2);
        myAppDatabase = Room.databaseBuilder(this, AppDatabase.class, "Favourite").build();
        populateRecyclerView();

       /* AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production").allowMainThreadQueries().build();

        List<Favourite> favList = db.favouriteDao().getAll();

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        // Adapter useresAdapter = new Adapter(Zacasni.getUseres2());
        FavouriteAdapter favAdapter = new FavouriteAdapter((Context) favList, this);
        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
*/
    }

    private void populateRecyclerView() {
        new GetSeznamFavAsyncTask().execute();
    }

    public class GetSeznamFavAsyncTask  extends AsyncTask<Void, Integer, List<Favourite>> {

        @Override
        protected List<Favourite> doInBackground(Void... voids) {
            return FavListActivity.this.myAppDatabase.favouriteDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Favourite> osebe) {
            super.onPostExecute(osebe);
            if(osebe.size() ==  0) {
                return;
            }
            RecyclerView recyclerView = findViewById(R.id.fav_list_recView);
            FavouriteAdapter zaposleniAdapter = new FavouriteAdapter(osebe);
            recyclerView.setAdapter(zaposleniAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(FavListActivity.this));
            Toast.makeText(getApplicationContext(), "seznam posodobljen", Toast.LENGTH_SHORT).show();
        }
    }
}
