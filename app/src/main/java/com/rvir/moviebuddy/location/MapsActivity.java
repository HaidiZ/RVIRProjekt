package com.rvir.moviebuddy.location;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.location.adapters.PlacesListAdapter;
import com.rvir.moviebuddy.location.entities.PlacesResults;
import com.rvir.moviebuddy.location.entities.Result;
import com.rvir.moviebuddy.location.services.APIClient;
import com.rvir.moviebuddy.location.services.GoogleMapAPI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private EditText editTextKeyword;
    private Button buttonSearch;
    private ListView listViewPlaces;
    private Spinner spinnerType;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                //TODO request permission with rationale dialog
            }
        }).check();

        editTextKeyword = findViewById(R.id.editTextKeyword);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runMaps();
                //      loadData();
            }
        });
        listViewPlaces = findViewById(R.id.listViewPlaces);
        spinnerType = findViewById(R.id.spinnerType);

        loadData();
    }

    private void runMaps() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 1000, 1, locationListener);
        }
    }


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String keyword = editTextKeyword.getText().toString();
            String key = getText(R.string.google_maps_key).toString();
            String currentLocation = location.getLatitude() + "," + location.getLongitude();
            int radius = 1500;
            String type = spinnerType.getSelectedItem().toString();
            GoogleMapAPI googleMapAPI = APIClient.getClient().create(GoogleMapAPI.class);
            googleMapAPI.getNearBy(currentLocation, radius, type, keyword, key).enqueue(new Callback<PlacesResults>() {
                @Override
                public void onResponse(Call<PlacesResults> call, Response<PlacesResults> response) {
                    if (response.isSuccessful()) {
                        List<Result> results = response.body().getResults();
                        PlacesListAdapter placesListAdapter = new PlacesListAdapter(
                                getApplicationContext(), results);
                        listViewPlaces.setAdapter(placesListAdapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "Faileed", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<PlacesResults> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void loadData() {
        List<String> types = new ArrayList<>();
        // types.add("ATM");
        types.add("Cinema");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplication(),
                R.layout.support_simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(arrayAdapter);
    }
}
