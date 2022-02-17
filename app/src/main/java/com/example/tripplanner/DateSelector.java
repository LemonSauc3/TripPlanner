package com.example.tripplanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;


public class DateSelector extends AppCompatActivity {

    // Members
    // Buttons
    private Button cancelDate;
    private Button confirmDate;
    private Button leaveDateButton;
    private Button returnDateButton;

    // Date picker view
    private DatePickerDialog.OnDateSetListener leaveDatePicker;
    private DatePickerDialog.OnDateSetListener returnDatePicker;

    // Data types
    private String leaveDate;
    private String returnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selector);

        // Hiding the action bar, as its not needed in this screen
        ActionBar ab = getSupportActionBar();
        ab.hide();
        
        initDatePicker();

        // Setting up the buttons
        cancelDate = (Button) findViewById(R.id.date_cancel_button);
        confirmDate = (Button) findViewById(R.id.date_save_button);
        leaveDateButton = (Button) findViewById(R.id.date_leave_button);
        returnDateButton = (Button) findViewById(R.id.date_return_button);

        // Setting the default date
        leaveDateButton.setText(getTodaysDate());
        returnDateButton.setText(getTodaysDate());


        // Setting up the onClick for the buttons
        cancelDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(getApplicationContext(), TripCreator.class);
                setResult(Activity.RESULT_CANCELED, cancelIntent);
                finish();
            }
        });

        confirmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Setting up the datePickerButtons
        leaveDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(c.YEAR);
                int month = c.get(c.MONTH);
                int day = c.get(c.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getApplicationContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        leaveDatePicker,
                        year,
                        month,
                        day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                leaveDateButton.setText(date);
                returnDateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = android.R.style.Theme_Black;

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        switch(month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "JAN";
        }
    }
}