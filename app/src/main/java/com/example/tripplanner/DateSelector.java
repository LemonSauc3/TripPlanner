package com.example.tripplanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class DateSelector extends AppCompatActivity {

    //Members

    private DatePickerDialog.OnDateSetListener sDateSetListener;
    private DatePickerDialog.OnDateSetListener eDateSetListener;
    private Button sButton;
    private Button eButton;
    private int sYear;
    private int sMonth;
    private int sDayOfMonth;
    private int eYear;
    private int eMonth;
    private int eDayOfMonth;
    private final int end = 1;
    private final int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selector);

        // Hiding the action bar, as its not needed in this screen
        ActionBar ab = getSupportActionBar();
        ab.hide();

        sButton = findViewById(R.id.buttonStart);
        eButton = findViewById(R.id.buttonEnd);

        sYear = 0;
        sMonth = 0;
        sDayOfMonth = 0;

        eYear = 0;
        eMonth = 0;
        eDayOfMonth = 0;

        //Set up Start date button click event
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(c.YEAR);
                int month = c.get(c.MONTH);
                int day = c.get(c.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getApplicationContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        sDateSetListener,
                        year,
                        month,
                        day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //Set up listener for Start date button click event

        sDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = getDateFormat(year, month, dayOfMonth, start);

                sButton.setText(date);
                compareDates();
            }
        };

        //Set up end date button click event

        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(c.YEAR);
                int month = c.get(c.MONTH);
                int day = c.get(c.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getApplicationContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        eDateSetListener,
                        year,
                        month,
                        day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //Set up listener for end date button click event

        eDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = getDateFormat(year, month, dayOfMonth, end);

                eButton.setText(date);
                compareDates();
            }
        };


    }

    /*
       FUNCTION    :   getDateFormat
       DESCRIPTION :   This function returns a formatted date MONTH day year
       PARAMETERS  :   int year        :   year
                       int month       :   month
                       int dayOfMonth  :   day of month
                       int mode        :   0 for start date, 1 for end date
       RETURN      :   String          :   Formatted date to display as button text
    */
    private String getDateFormat(int year, int month, int dayOfMonth, int mode) {

        //Set start or end date ints to be validated by compareDates()

        if (mode == 0)
        {
            sYear = year;
            sMonth = month;
            sDayOfMonth = dayOfMonth;
        }
        else
        {
            eYear = year;
            eMonth = month;
            eDayOfMonth = dayOfMonth;
        }

        String date;
        if (month == 1)
            date = "JAN";
        else if (month == 2)
            date = "FEB";
        else if (month == 3)
            date = "MAR";
        else if (month == 4)
            date = "APR";
        else if (month == 5)
            date = "MAY";
        else if (month == 6)
            date = "JUN";
        else if (month == 7)
            date = "JUL";
        else if (month == 8)
            date = "AUG";
        else if (month == 9)
            date = "SEP";
        else if (month == 10)
            date = "OCT";
        else if (month == 11)
            date = "NOV";
        else
            date = "DEC";

        date += " "+ dayOfMonth + " " + year;

        return date;
    }

    /*
       FUNCTION    :    compareDates
       DESCRIPTION :    This function checks that the end date is >= start date
       PARAMETERS  :    void
       RETURN      :    void
    */
    private void compareDates()
    {
        //Only evaluate when both dates have been set
        if ((sDayOfMonth != 0) && (eDayOfMonth != 0))
        {
            TextView err = findViewById(R.id.errorTextView);

            //Clear UI

            err.setText("");

            if (sYear > eYear)
            {
                err.setText(R.string.dateError);
                setDefaultDates();
            }
            else if (sYear == eYear)
            {
                if (sMonth > eMonth)
                {
                    err.setText(R.string.dateError);
                    setDefaultDates();
                }
                else if (sMonth == eMonth)
                {
                    if (sDayOfMonth > eDayOfMonth)
                    {
                        err.setText(R.string.dateError);
                        setDefaultDates();
                    }
                }
            }
        }
    }

    /*
       FUNCTION    :    setDefaultDates
       DESCRIPTION :    This function sets the default date values for the start and end dates
       PARAMETERS  :    void
       RETURN      :    void
    */
    private void setDefaultDates()
    {
        sYear = 0;
        sMonth = 0;
        sDayOfMonth = 0;

        eYear = 0;
        eMonth = 0;
        eDayOfMonth = 0;

        sButton.setText(R.string.selectDate);
        eButton.setText(R.string.selectDate);
    }

    public void submitDates(View view)
    {
        Intent intent = new Intent(this, TripCreator.class);

        // get formatted dates as strings
        String startDate = getDateFormat(sYear, sMonth, sDayOfMonth, 0);
        String endDate = getDateFormat(eYear, eMonth, eDayOfMonth, 1);

        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);

        startActivity(intent);
    }


}