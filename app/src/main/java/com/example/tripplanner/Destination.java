package com.example.tripplanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Destination extends AppCompatActivity {

    // Members
    // Buttons
    private Button saveButton;
    private Button cancelButton;

    //EditText
    private EditText departureText;
    private EditText arrivalText;

    // Data types
    private String departure;
    private String arrival;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // Hiding the action bar, as its not needed in this screen
        ActionBar ab = getSupportActionBar();
        ab.hide();

        // Setting up the Buttons
        saveButton = (Button) findViewById(R.id.dest_save_button);
        cancelButton = (Button) findViewById(R.id.dest_cancel_button);

        // Setting up the EditText Fields
        departureText = (EditText) findViewById(R.id.edit_departure);
        arrivalText = (EditText) findViewById(R.id.edit_arrival);

        // Initializing the Data types
        departure = "";
        arrival = "";
        checked = false;

        // Adding the onClickListener to the Buttons
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(getApplicationContext(), TripCreator.class);
                checked = checkFields();
                if (checked) {
                    returnIntent.putExtra("departure", departure);
                    returnIntent.putExtra("arrival", arrival);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(getApplicationContext(), TripCreator.class);
                setResult(Activity.RESULT_CANCELED, cancelIntent);
                finish();
            }
        });
    }

    private boolean checkFields() {
        boolean ret = false;
        if (!departureText.getText().toString().matches("") &&
                !arrivalText.getText().toString().matches("")) {
            ret = true;
            departure = departureText.getText().toString();
            arrival = arrivalText.getText().toString();
        }
        return ret;
    }
}