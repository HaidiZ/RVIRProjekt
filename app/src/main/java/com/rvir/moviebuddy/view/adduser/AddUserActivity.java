package com.rvir.moviebuddy.view.adduser;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.rvir.moviebuddy.R;
import com.rvir.moviebuddy.dao.DbUtil;
import com.rvir.moviebuddy.dao.UserDao;
import com.rvir.moviebuddy.entity.User;
import com.rvir.moviebuddy.entity.UserType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity {

  final Calendar birthDateCalendar = Calendar.getInstance();

  private TextInputLayout userNameInputLayout;
  private TextInputLayout birthDateInputLayout;
  private Spinner statusSpinner;

  private UserDao userDao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_user);
    userDao = DbUtil.getDb(getApplicationContext()).userDao();

    ArrayList<String> userTypeValues = new ArrayList<>();
    for (UserType userType : UserType.values()) {
      userTypeValues.add(userType.getValue());
    }
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userTypeValues);
    statusSpinner = findViewById(R.id.spinnerStatus);
    statusSpinner.setAdapter(spinnerAdapter);

    userNameInputLayout = findViewById(R.id.inputLayoutUserName);
    birthDateInputLayout = findViewById(R.id.inputLayoutBirthDate);
    birthDateInputLayout.setOnClickListener(birthDateClickListener);
    birthDateInputLayout.getEditText().setOnClickListener(birthDateClickListener);

    MaterialButton buttonOk = findViewById(R.id.buttonOk);
    buttonOk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean canAdd = true;
        if (userNameInputLayout.getEditText().getText().toString().isEmpty()) {
          userNameInputLayout.setError("User name must not be empty");
          canAdd = false;
        }
        if (birthDateInputLayout.getEditText().getText().toString().isEmpty()) {
          birthDateInputLayout.setError("Birth date must not be empty");
          canAdd = false;
        }

        if (canAdd) {
          User newUser = new User();
          newUser.setUserName(userNameInputLayout.getEditText().getText().toString());
          newUser.setBirthDate(birthDateCalendar.getTime());
          newUser.setUserType(UserType.getEnum(statusSpinner.getSelectedItem().toString()));

          userDao.insertUser(newUser)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                  finish();
                }

                @Override
                public void onError(Throwable e) {
                  Log.e("ERROR", e.getMessage());
                }
              });
        }
      }
    });
  }

  private void updateBirthDateEditText() {
    String myFormat = "dd. MM. yyyy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    birthDateInputLayout.getEditText().setText(sdf.format(birthDateCalendar.getTime()));
  }

  View.OnClickListener birthDateClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      new DatePickerDialog(AddUserActivity.this, dateDialogListener, birthDateCalendar
          .get(Calendar.YEAR), birthDateCalendar.get(Calendar.MONTH),
          birthDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
  };

  DatePickerDialog.OnDateSetListener dateDialogListener = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
      birthDateCalendar.set(Calendar.YEAR, year);
      birthDateCalendar.set(Calendar.MONTH, monthOfYear);
      birthDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      updateBirthDateEditText();
    }
  };

}
