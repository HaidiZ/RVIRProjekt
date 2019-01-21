package com.rvir.moviebuddy.view.main;

import android.content.Intent;
import android.os.Bundle;
import com.rvir.moviebuddy.MovieBuddyApplication;
import com.rvir.moviebuddy.api.dto.movielist.MovieResults;
import com.rvir.moviebuddy.dao.DbUtil;
import com.rvir.moviebuddy.dao.UserDao;
import com.rvir.moviebuddy.entity.MoviesSortType;
import com.rvir.moviebuddy.location.MapsActivity;
import com.rvir.moviebuddy.view.adduser.AddUserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.rvir.moviebuddy.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private MoviesAdapter moviesAdapter;
  private RecyclerView moviesRecyclerView;
  private ProgressBar progressBar;
  private TextView currentPageText;
  private int currentPage = 1;
  private ImageButton nextPageButton;
  private ImageButton previousPageButton;
  private Spinner sortTypeSpinner;
  private MoviesSortType currentSortType = MoviesSortType.POPULARITY;

  private Button btnMaps;
  private Button btnList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    UserDao userDao = DbUtil.getDb(getApplicationContext()).userDao();

    userDao.getUsersCount()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<Integer>() {
          @Override
          public void onSuccess(Integer userSize) {
            Log.d("MAIN", "Število uporabnikov: " + userSize);
            if (userSize == 0) {
              //TODO gremo na pregled filmov
              Log.d("MAIN", "Gremo na pregled filmov");
              Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
              startActivity(intent);
            }
          }

          @Override
          public void onError(Throwable e) {
            Log.e("ERROR", e.getMessage());
          }
        });

    setContentView(R.layout.activity_main);

    ArrayList<String> sortTypeValues = new ArrayList<>();
    for (MoviesSortType sortType : MoviesSortType.values()) {
      sortTypeValues.add(sortType.getName());
    }

    currentPageText = findViewById(R.id.pageText);
    progressBar = findViewById(R.id.progressBar);
    moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
    moviesAdapter = new MoviesAdapter(this, this);
    moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    moviesRecyclerView.setAdapter(moviesAdapter);

    nextPageButton = findViewById(R.id.buttonPageNext);
    previousPageButton = findViewById(R.id.buttonPagePrevious);

    nextPageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        currentPage++;
        loadMovies(currentPage, currentSortType);
      }
    });

    previousPageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentPage > 1) {
          currentPage--;
          loadMovies(currentPage, currentSortType);
        }
      }
    });

    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sortTypeValues);
    sortTypeSpinner = findViewById(R.id.spinnerSortType);
    sortTypeSpinner.setAdapter(spinnerAdapter);

    sortTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MoviesSortType currentType = MoviesSortType.values()[position];
        Log.d("SORT_TYPE", currentType.getName());
        currentSortType = currentType;
        loadMovies(currentPage, currentSortType);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    loadMovies(currentPage, currentSortType);

    //gumb maps
    btnMaps = findViewById(R.id.buttonMaps);
    btnMaps.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openActivityMaps();
      }
    });

    btnList = findViewById(R.id.buttonList);
    btnList.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openActivityFavList();
      }
    });
  }

  public void openActivityMaps(){
    Intent intent = new Intent(this, MapsActivity.class);
    startActivity(intent);
  }

  public void openActivityFavList(){
    Intent intent = new Intent(this, FavListActivity.class);
    startActivity(intent);
  }


  private void loadMovies(int page, MoviesSortType moviesSortType) {
    if (page > 1) {
      previousPageButton.setVisibility(View.VISIBLE);
    } else {
      previousPageButton.setVisibility(View.INVISIBLE);
    }

    String currentPageTextString = "Page " + page;
    currentPageText.setText(currentPageTextString);

    moviesRecyclerView.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
    MovieBuddyApplication.getMovieApi()
        .getPopularMovies(page, moviesSortType.getValue())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<MovieResults>() {

          @Override
          public void onSuccess(MovieResults movieResults) {
            Log.d("SUCCESS", movieResults.toString());
            moviesRecyclerView.setVisibility(View.VISIBLE);
            moviesAdapter.setMovies(movieResults.getResults());
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onError(Throwable e) {
            progressBar.setVisibility(View.GONE);
            Log.e("ERROR", e.getMessage(), e);
          }
        });
  }
}
