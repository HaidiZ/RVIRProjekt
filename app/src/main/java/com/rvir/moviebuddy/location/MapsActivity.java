package com.rvir.moviebuddy.location;
import com.rvir.moviebuddy.R;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private EditText editTextKeyword;
    private Button buttonSearch;
    private ListView listViewPlaces;
    private Spinner spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        editTextKeyword = findViewById(R.id.editTextKeyword);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSearch_onClick(view);
            }
        });
        listViewPlaces = findViewById(R.id.listViewPlaces);
        spinnerType = findViewById(R.id.spinnerType);
    }

    private void buttonSearch_onClick(View view) {


    }

    private void loadData(){

        List<String> types = new ArrayList<>();
        types.add("ATM");
        types.add("Cinema");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplication(),
                R.layout.support_simple_spinner_dropdown_item, types);

        spinnerType.setAdapter(arrayAdapter);

    }
}
