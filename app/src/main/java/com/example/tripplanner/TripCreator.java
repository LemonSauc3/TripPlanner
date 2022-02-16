package com.example.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TripCreator extends AppCompatActivity {

    // Members
    // Buttons
    private Button cancelTrip;
    private Button createTrip;
    private Button addDest;
    private Button addDate;
    private Button addMembers;

    // TextViews
    private TextView departureView;
    private TextView arrivalView;

    // TripHandler
    private TripHandler tHandler;

    // Data types
    private String arrival;
    private String departure;
    private String leaveDate;
    private String returnDate;
    private ArrayList<String> members;

    // Request Codes
    private int DEST_CODE = 1;
    private int DATE_CODE = 2;
    private int MEMBER_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_creator);

        // Hiding the action bar, as its not needed in this screen
        ActionBar ab = getSupportActionBar();
        ab.hide();

        // Grabbing the buttons id
        cancelTrip = (Button) findViewById(R.id.trip_cancel_button);
        createTrip = (Button) findViewById(R.id.trip_creation_button);
        addDest = (Button) findViewById(R.id.trip_dest_button);
        addDate = (Button) findViewById(R.id.trip_dates_button);
        addMembers = (Button) findViewById(R.id.trip_people_button);

        // Initializing the TextViews
        departureView = (TextView) findViewById(R.id.departure_view);
        arrivalView = (TextView) findViewById(R.id.arrival_view);

        // Initializing the Data types
        arrival = "";
        departure = "";
        leaveDate = "";
        returnDate = "";
        members = new ArrayList<String>();

        // Importing the TripHandler to handle the storing of information
        tHandler = new TripHandler(arrival, departure, leaveDate, returnDate, members);

        // Displaying the information
        departureView.setText(tHandler.getDeparture());
        arrivalView.setText(tHandler.getArrival());

        // Creating Button 'onClick' Methods
        cancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTripIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(createTripIntent);
            }
        });

        addDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent destIntent = new Intent(getApplicationContext(), Destination.class);
                startActivityForResult(destIntent, DEST_CODE);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dateIntent = new Intent(getApplicationContext(), DateSelector.class);
                startActivityForResult(dateIntent, DATE_CODE);
            }
        });

        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Checking the code for the destination activity
        if (requestCode == DEST_CODE) {
            Toast.makeText(getApplicationContext(), "DEST_CODE", Toast.LENGTH_SHORT).show();
            if (resultCode == Activity.RESULT_OK) {
                departure = data.getStringExtra("departure");
                arrival = data.getStringExtra("arrival");
                tHandler.setArrival(arrival);
                tHandler.setDeparture(departure);

                // Displaying the information
                departureView.setText(tHandler.getDeparture());
                arrivalView.setText(tHandler.getArrival());
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "CANCELLED", Toast.LENGTH_SHORT).show();
            }
        }

        // Checking the code for the date activity
        else if (requestCode == DATE_CODE) {
            Toast.makeText(getApplicationContext(), "DATE_CODE", Toast.LENGTH_SHORT).show();
            if (resultCode == Activity.RESULT_OK) {

            }
            else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }

        // Checking the code for the members activity
        else if (requestCode == MEMBER_CODE) {
            Toast.makeText(getApplicationContext(), "MEMBER_CODE", Toast.LENGTH_SHORT).show();
            if (resultCode == Activity.RESULT_OK) {

            }
            else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }

    }
}